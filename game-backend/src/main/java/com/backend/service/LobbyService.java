package com.backend.service;

import com.backend.model.Lobby;
import com.backend.util.HibernateUtil;

public class LobbyService {
    private static LobbyService lobby;
    private HibernateUtil hibernate;

    private LobbyService() {
        this.hibernate = HibernateUtil.getInstance();
    }

    public static LobbyService getInstance() {
        if (LobbyService.lobby == null) {
            LobbyService.lobby = new LobbyService();
        }
        return LobbyService.lobby;
    }

    public void registerLobby(Lobby lobby) {
        this.hibernate.persist(lobby);
    }
}
