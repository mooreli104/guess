package com.backend.service;

import org.hibernate.Hibernate;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.util.HibernateUtil;

public class PlayerService {

    private static PlayerService userService;

    private HibernateUtil hibernate;

    private PlayerService() {
        this.hibernate = HibernateUtil.getInstance();
    }

    public static PlayerService getInstance() {
        if (PlayerService.userService == null) {
            PlayerService.userService = new PlayerService();
        }
        return PlayerService.userService;
    }

    public void registerPlayer(Player player) {
        this.hibernate.persist(player);
    }

    public void join(Player player, Lobby lobby) {
        player.setLobby(lobby);
    }
}
