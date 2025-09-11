package com.backend.controller.endpoints;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/user")
public class UserEndpoint {

    public UserEndpoint() {
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("Socket connected: " + session);
    }

    @OnMessage
    public void onWebSocketText(Session session, String message) {
        System.out.println("Received: " + message);

    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket closed: " + reason);
    }

}
