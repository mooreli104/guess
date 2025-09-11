package com.backend.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    private UUID uuid;
    private String username;
    private int score;
    private String guess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lobbyID", referencedColumnName = "lobbyID")
    private Lobby lobby;

    public Player() {
    }

    public Player(String username) {
        this.username = username;
        this.uuid = UUID.randomUUID();
        this.score = 0;
        this.guess = null;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }

}
