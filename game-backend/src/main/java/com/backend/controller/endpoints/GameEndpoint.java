package com.backend.controller.endpoints;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.service.GameService;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/game")
public class GameEndpoint {

    @OnOpen
    public void onWebSocketOpen(Session session) {
        System.out.println(session.getId() + " " + "has connected");
    }

    @OnMessage
    public void onWebSocketText(Session session, String message) {
        createLobby(message, session.getId());
    }

    @OnClose
    public void onWebSocketClose(Session session, CloseReason reason) {
        leaveLobby(session.getId());
        System.out.println(session.getId() + "disconnected");
    }

    public void createLobby(String playerName, String session) {
        Player newPlayer = new Player(playerName, session);
        Lobby newLobby = new Lobby();

        GameService.getInstance().createLobby(newLobby, newPlayer);
    }

    public void leaveLobby(String session) {
        GameService.getInstance().leaveLobby(session);
    }

}
