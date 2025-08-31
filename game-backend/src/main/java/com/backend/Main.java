package com.backend;

import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;

import com.backend.webserver.MyServlet;

public class Main {
    public static void main(String[] args) {

        // Create a Server with a ServerConnector listening on port 8080.
        Server server = new Server(8080);

        // Create a ServletContextHandler with the given context path.
        ServletContextHandler handler = new ServletContextHandler("/");
        server.setHandler(handler);

        // Ensure that JavaxWebSocketServletContainerInitializer is initialized,
        // to setup the ServerContainer for this web application context.
        JakartaWebSocketServletContainerInitializer.configure(handler, null);

        // Add a WebSocket-initializer Servlet to register WebSocket endpoints.
        handler.addServlet(MyServlet.class, "/*");

        // Starting the Server will start the ServletContextHandler.
        try {
            server.start();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}