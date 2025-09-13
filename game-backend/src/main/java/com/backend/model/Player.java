package com.backend.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    public void guess(String guess) {
        this.guess = guess;
    }

    public void joinLobby(Lobby lobby) {

        this.lobby = lobby;
    }

    public String getUsername() {
        return username;
    }

    public String getGuess() {
        return guess;
    }

    public int getScore() {
        return score;
    }

    public void leaveLobby() {
        this.lobby = null;
    }

}
