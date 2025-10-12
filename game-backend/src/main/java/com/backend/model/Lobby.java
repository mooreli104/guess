package com.backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Lobby {
    private UUID lobbyID;
    private Anime anime;
    @JsonBackReference
    private Set<Player> players;

    public Lobby() {
        this.lobbyID = UUID.randomUUID();
        this.anime = null;
        this.players = new HashSet<>();
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

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Anime getAnime() {
        return anime;
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

}
