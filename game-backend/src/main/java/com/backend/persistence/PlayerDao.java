package com.backend.persistence;

import java.util.Set;
import java.util.UUID;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.util.HibernateUtil;

public class PlayerDao implements BaseDao<Player> {
    private static PlayerDao playerDao;

    private PlayerDao() {
    }

    public static PlayerDao getInstance() {
        if (PlayerDao.playerDao == null) {
            PlayerDao.playerDao = new PlayerDao();
        }
        return PlayerDao.playerDao;
    }

    @Override
    public void save(Player player) {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.persist(player);
        });
    }

    @Override
    public Player findById(String id) {
        Player player = HibernateUtil.getInstance().fromTransaction(session -> session.byNaturalId(Player.class)
                .using("session", id)
                .load());
        return player;
    }

    public Player findById(UUID id) {
        Player player = HibernateUtil.getInstance().fromTransaction(session -> session.find(Player.class, id));
        return player;
    }

    @Override
    public void update() {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.flush();
        });
    }

    @Override
    public void merge(Player player) {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.merge(player);
        });
    }

    public Set<Player> getPlayers(String id) {
        Player player = findById(id);
        Lobby lobby = player.getLobby();
        Set<Player> players = lobby.getPlayers();
        return players;
    }

    public Lobby getLobby(UUID id) {
        var graph = HibernateUtil.getInstance().createEntityGraph(Player.class);
        graph.addSubgraph("lobby");
        Player player = HibernateUtil.getInstance().createEntityManager().find(graph, id);
        return player.getLobby();
    }

    @Override
    public void remove(Player player) {
        HibernateUtil.getInstance().inTransaction(session -> {
            Player persistentPlayer = session.merge(player);
            session.remove(persistentPlayer);
        });
    }

}
