package com.backend.model;

import java.util.UUID;

import org.hibernate.annotations.NaturalId;

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
    @NaturalId
    private String session;
    private int score;
    private String guess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lobbyID", referencedColumnName = "lobbyID")
    private Lobby lobby;

    public Player() {
    }

    public Player(String username, String session) {
        this.username = username;
        this.score = 0;
        this.guess = null;
        this.session = session;
    }

    public void guess(String guess) {
        this.guess = guess;
    }

    public void joinLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public String getUsername() {
        return this.username;
    }

    public String getGuess() {
        return this.guess;
    }

    public int getScore() {
        return this.score;
    }

    public UUID getId() {
        return this.uuid;
    }

    public void leaveLobby() {
        this.lobby = null;
    }

    public Lobby getLobby() {
        return this.lobby;
    }

    @Override
    public String toString() {
        return this.username + " " + this.session;
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
