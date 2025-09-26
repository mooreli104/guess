package com.backend.controller.endpoints;

import java.util.Set;
import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint;
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
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonRootNode = objectMapper.readTree(message);
            String action = jsonRootNode.get("action").asText();
            RemoteEndpoint.Async rep = session.getAsyncRemote();

            switch (action) {
                case "createLobby":
                    String username = jsonRootNode.get("username").asText();
                    createLobby(username, session.getId());

                    break;
                case "joinLobby":

                    break;

                default:
                    break;
            }
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }

    @OnClose
    public void onWebSocketClose(Session session, CloseReason reason) {
        String id = session.getId();
        System.out.println(id + " disconnected");
        leaveLobby(id);
    }

    public void createLobby(String playerName, String session) {
        Player newPlayer = new Player(playerName, session);
        Lobby newLobby = new Lobby();
        GameService game = GameService.getInstance();
        game.createLobby(newLobby, newPlayer);

    }

    public void leaveLobby(String session) {
        GameService.getInstance().leaveLobby(session);
    }

    public Set<Player> getPlayers(String session) {
        Set<Player> players = GameService.getInstance().fetchPlayers(session);
        return players;
    }

}
