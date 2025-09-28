package com.backend.model;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Lobby")
public class Lobby {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID lobbyID;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lobby", orphanRemoval = true)
    private Set<Player> players;

    public Lobby() {
    }

    public UUID getId() {
        return this.lobbyID;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addPlayer(Collection<Player> players) {
        this.players.addAll(players);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public String toString() {
        return this.lobbyID.toString();
    }

    @Override
    public int hashCode() {
        return this.lobbyID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Lobby))
            return false;
        Lobby lobby = (Lobby) obj;
        return this.lobbyID == lobby.lobbyID;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

}
