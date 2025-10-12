package com.backend.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Player {

    public enum Role {
        HOST,
        PLAYER;
    }

    private UUID uuid;
    private String username;
    private int score;
    private String guess;
    private Role role;

    @JsonManagedReference
    private Lobby lobby;

    public Player() {
    }

    public Player(String username, String websocketSession) {
        this.username = username;
        this.score = 0;
        this.guess = null;
        this.uuid = UUID.fromString(websocketSession);
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuess() {
        return this.guess;
    }

    public void joinLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public void leaveLobby() {
        this.lobby = null;
    }

    public Lobby getLobby() {
        return this.lobby;
    }

    public String getUsername() {
        return this.username;
    }

    public int getScore() {
        return this.score;
    }

    public UUID getId() {
        return this.uuid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.username + " " + this.uuid + this.lobby;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Player))
            return false;
        Player player = (Player) obj;
        return player.uuid.equals(this.uuid);
    }

}
