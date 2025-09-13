package com.backend;

import java.util.Set;

import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.CrossOriginHandler;

import com.backend.controller.servlets.GameServlet;
import com.backend.controller.servlets.WebSocketInitServlet;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(8080);

        ContextHandlerCollection contexts = new ContextHandlerCollection();

        ServletContextHandler handler = new ServletContextHandler("/");
        CrossOriginHandler crossOriginHandler = new CrossOriginHandler();

        crossOriginHandler.setAllowedOriginPatterns(Set.of("http://localhost:5173"));

        contexts.setHandlers(crossOriginHandler, handler);
        server.setHandler(contexts);

        // Ensure that JavaxWebSocketServletContainerInitializer is initialized,
        // to setup the ServerContainer for this web application context.
        JakartaWebSocketServletContainerInitializer.configure(handler, null);

        handler.addServlet(WebSocketInitServlet.class, "/ws");
        handler.addServlet(GameServlet.class, "/api");

        try {
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}