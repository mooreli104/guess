package com.backend.service;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.persistence.LobbyDao;
import com.backend.persistence.UserDao;

public class GameService {
    private static GameService game;

    private GameService() {
    }

    public static GameService getInstance() {
        if (GameService.game == null) {
            GameService.game = new GameService();
        }
        return GameService.game;
    }

    public void createLobby(Lobby lobby, Player player) {
        LobbyDao lobbyDao = LobbyDao.getInstance();
        UserDao playerDao = UserDao.getInstance();

        lobbyDao.registerLobby(lobby);
        player.joinLobby(lobby);
        playerDao.registerPlayer(player);

    }
}
