package com.backend.service;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.persistence.LobbyDao;
import com.backend.persistence.PlayerDao;

public class GameService {
    private static GameService game;
    private LobbyDao lobbyDao = LobbyDao.getInstance();
    private PlayerDao playerDao = PlayerDao.getInstance();

    private GameService() {
    }

    public static GameService getInstance() {
        if (GameService.game == null) {
            GameService.game = new GameService();
        }
        return GameService.game;
    }

    public void createLobby(Lobby lobby, Player player) {
        this.lobbyDao.save(lobby);
        player.joinLobby(lobby);
        this.playerDao.save(player);
    }

    public void leaveLobby(String session) {
        Player player = this.playerDao.findById(session);
        player.leaveLobby();
        this.playerDao.update();

    }
}
