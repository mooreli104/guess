package com.backend.persistence;

import com.backend.model.Lobby;
import com.backend.util.HibernateUtil;

public class LobbyDao implements BaseDao<Lobby> {
    private static LobbyDao lobbyDao;

    private LobbyDao() {
        LobbyDao.lobbyDao = new LobbyDao();
    }

    public static LobbyDao getInstance() {
        if (LobbyDao.lobbyDao == null) {
            LobbyDao.lobbyDao = new LobbyDao();
        }
        return LobbyDao.lobbyDao;
    }

    @Override
    public void save(Lobby lobby) {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.persist(lobby);
        });
    }

    @Override
    public Lobby findById(String id) {
        Lobby lobby = HibernateUtil.getInstance().fromTransaction(session -> session.byNaturalId(Lobby.class)
                .using("session", id)
                .load());
        return lobby;
    }

    @Override
    public void update() {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.flush();
        });
    }

}
