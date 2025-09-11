package com.backend;

import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;

import com.backend.controller.servlets.Servlet;
import com.backend.controller.servlets.WebSocketInitServlet;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler("/");
        server.setHandler(handler);

        // Ensure that JavaxWebSocketServletContainerInitializer is initialized,
        // to setup the ServerContainer for this web application context.
        JakartaWebSocketServletContainerInitializer.configure(handler, null);

        handler.addServlet(WebSocketInitServlet.class, "/ws");
        handler.addServlet(Servlet.class, "/api");

        try {
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}