package com.db.desafio_votacao.controllers.ui;

import com.db.desafio_votacao.dto.out.ui.ActionButtonDTO;
import com.db.desafio_votacao.dto.out.ui.FormFieldDTO;
import com.db.desafio_votacao.dto.out.ui.ScreenDTO;
import com.db.desafio_votacao.dto.out.ui.SelectionItemDTO;
import com.db.desafio_votacao.dto.out.vote.VoteResultDTO;
import com.db.desafio_votacao.entities.topics.Topic;
import com.db.desafio_votacao.repositories.topics.TopicRepository;
import com.db.desafio_votacao.services.result.VoteResultService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ui/topics")
public class MobileUiController {

    private final String baseUrl;
    private final TopicRepository topicRepository;
    private final VoteResultService voteResultService;

    public MobileUiController(@Value("${app.base-url}") String baseUrl, TopicRepository topicRepository, VoteResultService voteResultService) {
        this.baseUrl = baseUrl;
        this.topicRepository = topicRepository;
        this.voteResultService = voteResultService;
    }

    @GetMapping("/form")
    public ScreenDTO createTopicForm() {
        ScreenDTO screen = new ScreenDTO();
        screen.setType("FORMULARIO");
        screen.setTitle("Criar nova pauta");
        screen.setDescription("Informe os dados da pauta que deseja criar.");

        FormFieldDTO titleField = new FormFieldDTO();
        titleField.setId("title");
        titleField.setLabel("Título da pauta");
        titleField.setType("TEXTO");
        titleField.setRequired(true);

        FormFieldDTO descriptionField = new FormFieldDTO();
        descriptionField.setId("description");
        descriptionField.setLabel("Descrição");
        descriptionField.setType("TEXTO_LONGO");
        descriptionField.setRequired(false);

        screen.setItems(List.of(titleField, descriptionField));

        ActionButtonDTO createButton = new ActionButtonDTO();
        createButton.setLabel("Criar pauta");
        createButton.setUrl(baseUrl + "/api/v1/topics");

        createButton.setBody(Map.of(
                "title", "",
                "description", ""
        ));

        screen.setActions(List.of(createButton));

        return screen;
    }

    @GetMapping
    public ScreenDTO listTopicsSelection() {
        List<Topic> topics = topicRepository.findAll();

        ScreenDTO screen = new ScreenDTO();
        screen.setType("SELECAO");
        screen.setTitle("Pautas disponíveis");
        screen.setDescription("Selecione uma pauta para votar ou ver detalhes.");

        List<SelectionItemDTO> options = topics.stream().map(topic -> {
            SelectionItemDTO item = new SelectionItemDTO();
            item.setLabel(topic.getTitle());
            item.setDescription(topic.getDescription());

            item.setUrl(baseUrl + "/api/v1/ui/topics/" + topic.getId() + "/vote");

            item.setBody(Map.of("topicId", topic.getId()));

            return item;
        }).toList();

        screen.setOptions(options);
        return screen;
    }

    @GetMapping("/{topicId}/session/open")
    public ScreenDTO openSessionForm(@PathVariable String topicId) {
        ScreenDTO screen = new ScreenDTO();
        screen.setType("FORMULARIO");
        screen.setTitle("Abrir sessão de votação");
        screen.setDescription("Defina a duração (em minutos) da sessão para esta pauta.");

        FormFieldDTO durationField = new FormFieldDTO();
        durationField.setId("duration");
        durationField.setLabel("Duração (minutos)");
        durationField.setType("NUMERICO");
        durationField.setRequired(false);

        screen.setItems(List.of(durationField));

        ActionButtonDTO openButton = new ActionButtonDTO();
        openButton.setLabel("Abrir sessão");

        openButton.setUrl(baseUrl + "/api/v1/topics/" + topicId + "/sessions");

        openButton.setBody(Map.of("duration", 1));

        screen.setActions(List.of(openButton));

        return screen;
    }

    @GetMapping("/{topicId}/vote")
    public ScreenDTO voteForm(@PathVariable String topicId) {
        ScreenDTO screen = new ScreenDTO();
        screen.setType("FORMULARIO");
        screen.setTitle("Votar na pauta");
        screen.setDescription("Informe seu CPF e seu voto.");

        FormFieldDTO cpfField = new FormFieldDTO();
        cpfField.setId("cpf");
        cpfField.setLabel("CPF");
        cpfField.setType("TEXTO");
        cpfField.setRequired(true);

        FormFieldDTO voteField = new FormFieldDTO();
        voteField.setId("vote");
        voteField.setLabel("Seu voto (true = SIM, false = NÃO)");
        voteField.setType("BOOLEAN");
        voteField.setRequired(true);

        screen.setItems(List.of(cpfField, voteField));

        ActionButtonDTO voteButton = new ActionButtonDTO();
        voteButton.setLabel("Enviar voto");

        voteButton.setUrl(baseUrl + "/api/v1/topics/" + topicId + "/vote");

        voteButton.setBody(Map.of(
                "cpf", "",
                "vote", true
        ));

        screen.setActions(List.of(voteButton));

        return screen;
    }

    @GetMapping("/{topicId}/result")
    public ScreenDTO resultScreen(@PathVariable String topicId) {
        VoteResultDTO result = voteResultService.calculateResult(topicId);

        ScreenDTO screen = new ScreenDTO();
        screen.setType("FORMULARIO");
        screen.setTitle("Resultado da votação");
        screen.setDescription(result.getTopicTitle());

        FormFieldDTO totalField = new FormFieldDTO();
        totalField.setId("total");
        totalField.setLabel("Total de votos: " + result.getTotalVotes());
        totalField.setType("NUMERICO");
        totalField.setRequired(false);

        FormFieldDTO yesField = new FormFieldDTO();
        yesField.setId("yes");
        yesField.setLabel("Votos SIM: "+ result.getYesVotes());
        yesField.setType("NUMERICO");
        yesField.setRequired(false);

        FormFieldDTO noField = new FormFieldDTO();
        noField.setId("no");
        noField.setLabel("Votos NÃO: " + result.getNoVotes());
        noField.setType("NUMERICO");
        noField.setRequired(false);

        screen.setItems(List.of(totalField, yesField, noField));

        screen.setActions(List.of());
        return screen;
    }
}

