package com.backend.model;

import java.util.Map;

enum State {
    WAITING,
    PLAYING,
    STOPPED;

}

public class Game {
    private String[] correctGuesses;
    private State state;
    private Map<Player, String> playerGuesses;
    private Anime anime;
}
