package com.backend.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lobby")
public class Lobby {
    @Id
    private UUID lobbyID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lobby")
    private Set<Player> players;

    public Lobby() {
    }

    public Lobby(UUID uuid) {
        this.lobbyID = uuid;
    }

    public void setLobbyID() {
        this.lobbyID = UUID.randomUUID();
    }

    public UUID getLobbyID() {
        return this.lobbyID;
    }

    @Override
    public String toString() {
        return this.lobbyID.toString();
    }

}
