package com.backend.model;

import java.util.UUID;

public class Player {
    private String username;
    private UUID uuid;

    public Player() {
    }

    public Player(String username) {
        this.username = username;
        this.uuid = UUID.randomUUID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void setUUID() {
        this.uuid = UUID.randomUUID();
    }

}
