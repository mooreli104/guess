package com.backend.persistence;

import com.backend.model.Player;
import com.backend.util.HibernateUtil;

public class PlayerDao implements BaseDao<Player> {
    private static PlayerDao playerDao;

    private PlayerDao() {
        PlayerDao.playerDao = new PlayerDao();
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

    @Override
    public void update() {
        HibernateUtil.getInstance().inTransaction(session -> {
            session.flush();
        });
    }

}
