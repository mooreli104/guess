package com.backend.persistence;

import java.util.Set;
import java.util.UUID;

import com.backend.model.Anime;
import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.util.HibernateUtil;

public class LobbyDao implements BaseDao<Lobby> {
    private static LobbyDao lobbyDao;

    private LobbyDao() {
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

    public Lobby findById(UUID id) {
        Lobby lobby = HibernateUtil.getInstance().fromTransaction(session -> session.find(Lobby.class, id));
        return lobby;
    }

    @Override
    public void update() {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.flush();
        });
    }

    @Override
    public void merge(Lobby lobby) {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.merge(lobby);
        });
    }

    @Override
    public void remove(Lobby lobby) {
        HibernateUtil.getInstance().inTransaction(session -> {
            Lobby persistentLobby = session.merge(lobby);
            session.remove(persistentLobby);
        });
    }

    public Set<Player> getPlayers(UUID id) {
        var graph = HibernateUtil.getInstance().createEntityGraph(Lobby.class);
        graph.addSubgraph("players");
        Lobby lobby = HibernateUtil.getInstance().createEntityManager().find(graph, id);
        return lobby.getPlayers();
    }

    public void setAnime(Anime anime) {

    }
}
