package com.backend.persistence;

import com.backend.model.Player;
import com.backend.util.HibernateUtil;

public class UserDao {
    private static UserDao userDao;
    private HibernateUtil hibernate;

    private UserDao() {
        this.hibernate = HibernateUtil.getInstance();
    }

    public static UserDao getInstance() {
        if (UserDao.userDao == null) {
            UserDao.userDao = new UserDao();
        }
        return UserDao.userDao;
    }

    public void registerPlayer(Player player) {
        this.hibernate.persist(player);
    }

}
