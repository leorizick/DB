package com.db.desafio_votacao.dto.out.vote;

public class VoteResultDTO {
    private String topicId;
    private String topicTitle;

    private long totalVotes;
    private long yesVotes;
    private long noVotes;


    public VoteResultDTO() {
    }

    public VoteResultDTO(String topicId, String topicTitle, long totalVotes, long yesVotes, long noVotes) {
        this.topicId = topicId;
        this.topicTitle = topicTitle;
        this.totalVotes = totalVotes;
        this.yesVotes = yesVotes;
        this.noVotes = noVotes;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public long getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(long yesVotes) {
        this.yesVotes = yesVotes;
    }

    public long getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(long noVotes) {
        this.noVotes = noVotes;
    }
}
