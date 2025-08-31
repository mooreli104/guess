package com.backend.webserver;

import java.io.IOException;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class MyEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket connected: " + session);
        try {
            session.getBasicRemote().sendText("HELLO");
        } catch (IOException e) {

        }
    }

}
