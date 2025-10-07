package com.backend.model;

public class Anime {
    private String url;
    private String[] correctNames;

    public String[] getCorrectNames() {
        return correctNames;
    }

    public void setCorrectNames(String[] correctNames) {
        this.correctNames = correctNames;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
