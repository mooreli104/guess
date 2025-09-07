package com.backend.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lobby")

public class Lobby {
    @Id
    private UUID lobbyID;

    public Lobby() {
    }

    public void setLobbyID() {
        this.lobbyID = UUID.randomUUID();
    }

    public UUID getLobbyID() {
        return this.lobbyID;
    }

}
