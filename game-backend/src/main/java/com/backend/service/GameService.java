package com.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.backend.model.Lobby;
import com.backend.model.Player;

public class GameService {
    private static GameService game;
    private static Map<UUID, Lobby> lobbies;
    private static Map<UUID, Player> players;

    private GameService() {
        GameService.lobbies = new HashMap<>();
        GameService.players = new HashMap<>();
    }

    public static GameService getInstance() {

        if (GameService.game == null) {
            GameService.game = new GameService();
        }
        return GameService.game;
    }

    public void createLobby(Player player) {
        Lobby lobby = new Lobby();
        player.joinLobby(lobby);
        lobby.addPlayer(player);
        GameService.players.put(player.getId(), player);
        GameService.lobbies.put(lobby.getId(), lobby);

    }

    public void leaveLobby(String playerID) {
        Player player = GameService.players.get(UUID.fromString(playerID));
        Lobby lobby = player.getLobby();
        lobby.removePlayer(player);
        GameService.lobbies.put(lobby.getId(), lobby);
        GameService.players.remove(UUID.fromString(playerID));
    }

    public Lobby getLobby(String lobbyID) {
        return GameService.lobbies.get(UUID.fromString(lobbyID));
    }

    public void updateLobby(Lobby lobby) {
        GameService.lobbies.put(lobby.getId(), lobby);
    }

    public Player getPlayer(String playerID) {
        return GameService.players.get(UUID.fromString(playerID));
    }

    public void checkGuess(Player player, Lobby lobby) {
        System.out.println("check");
        // System.out.println(player.getGuess() + " " + lobby.getAnime().getTitle());
    }
}
