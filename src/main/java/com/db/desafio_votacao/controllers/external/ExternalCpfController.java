package com.db.desafio_votacao.controllers.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/external/cpf")
public class ExternalCpfController {

    private final Random random = new Random();

    @GetMapping("/{cpf}")
    public ResponseEntity<Map<String, String>> validarCpf(@PathVariable String cpf) {

        if (cpf == null || !cpf.matches("\\d{11}")) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "INVALID_CPF"
            ));
        }

        boolean ableToVote = random.nextBoolean();

        if (!ableToVote) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", "UNABLE_TO_VOTE"
            ));
        }

        return ResponseEntity.ok(Map.of("status", "ABLE_TO_VOTE"));
    }
}
