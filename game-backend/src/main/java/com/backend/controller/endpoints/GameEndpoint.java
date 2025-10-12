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
            Lobby lobby;
            Player player;
            switch (action) {
                case "createLobby":
                    String username = jsonRootNode.get("username").asText();
                    player = new Player(username, session.getId());
                    player.setRole(Player.Role.HOST);
                    GameService.getInstance().createLobby(player);

                    break;
                case "guess":
                    player = GameService.getInstance().getPlayer(session.getId());
                    lobby = player.getLobby();
                    String guess = jsonRootNode.get("guess").asText();
                    player.setGuess(guess);
                    System.out.println(lobby.getAnime());
                    GameService.getInstance().checkGuess(player, lobby);
                    break;
                case "getPlayers":
                    try {
                        lobby = GameService.getInstance().getPlayer(session.getId()).getLobby();
                        Set<Player> players = lobby.getPlayers();
                        String jsonPlayers = objectMapper.writeValueAsString(players);
                        rep.sendText(jsonPlayers);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

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
        String playerID = session.getId();
        System.out.println(playerID + " disconnected");
        GameService.getInstance().leaveLobby(playerID);
    }
}
