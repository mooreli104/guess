package com.backend;

import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;

import com.backend.database.Database;
import com.backend.webserver.servlets.LobbyServlet;
import com.backend.webserver.servlets.Servlet;
import com.backend.webserver.servlets.WebSocketInitServlet;

public class Main {
    public static void main(String[] args) {

        // Create a Server with a ServerConnector listening on port 8080.
        Server server = new Server(8080);
        Database database = new Database();

        // Create a ServletContextHandler with the given context path.
        ServletContextHandler handler = new ServletContextHandler("/");
        server.setHandler(handler);

        // Ensure that JavaxWebSocketServletContainerInitializer is initialized,
        // to setup the ServerContainer for this web application context.
        JakartaWebSocketServletContainerInitializer.configure(handler, null);

        // Add a WebSocket-initializer Servlet to register WebSocket endpoints.
        handler.addServlet(WebSocketInitServlet.class, "/ws");
        handler.addServlet(Servlet.class, "/api");

        // Starting the Server will start the ServletContextHandler.
        try {
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}