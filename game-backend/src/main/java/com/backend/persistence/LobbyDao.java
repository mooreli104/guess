package com.backend.persistence;

import com.backend.model.Lobby;
import com.backend.util.HibernateUtil;

public class LobbyDao {
    private static LobbyDao lobbyDao;
    private HibernateUtil hibernate;

    private LobbyDao() {
        this.hibernate = HibernateUtil.getInstance();
    }

    public static LobbyDao getInstance() {
        if (LobbyDao.lobbyDao == null) {
            LobbyDao.lobbyDao = new LobbyDao();
        }
        return LobbyDao.lobbyDao;
    }

    public void registerLobby(Lobby lobby) {
        this.hibernate.persist(lobby);
    }

}
