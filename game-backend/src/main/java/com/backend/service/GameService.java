package com.backend.service;

import java.util.Set;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.persistence.BaseDao;
import com.backend.persistence.LobbyDao;
import com.backend.persistence.PlayerDao;

public class GameService {
    private static GameService game;
    private BaseDao<Player> playerDao = PlayerDao.getInstance();
    private BaseDao<Lobby> lobbyDao = LobbyDao.getInstance();

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
        try {
            Player player = this.playerDao.findById(session);
            Lobby lobby = ((PlayerDao) this.playerDao).getLobby(player.getId());
            this.playerDao.remove(player);
            if (lobby.getPlayers().size() == 0) {
                this.lobbyDao.remove(lobby);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Set<Player> fetchPlayers(String session) {
        Set<Player> players = ((PlayerDao) this.playerDao).getPlayers(session);
        return players;
    }

    public Lobby getLobby(String session) {
        Player player = this.playerDao.findById(session);
        Lobby lobby = ((PlayerDao) this.playerDao).getLobby(player.getId());
        return lobby;
    }

}
