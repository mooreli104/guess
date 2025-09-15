package com.backend;

import java.util.Set;
import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Context;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.CrossOriginHandler;

import com.backend.controller.endpoints.GameEndpoint;
import com.backend.controller.servlets.GameServlet;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(8080);

        ContextHandlerCollection contexts = new ContextHandlerCollection();

        ServletContextHandler socketHandler = new ServletContextHandler("/ws/*");
        socketHandler.addServlet(GameServlet.class, "/api/*");

        CrossOriginHandler crossOriginHandler = new CrossOriginHandler();
        crossOriginHandler.setAllowedOriginPatterns(Set.of("http://localhost:5173"));
        contexts.setHandlers(crossOriginHandler, socketHandler);
        server.setHandler(contexts);

        // Ensure that JavaxWebSocketServletContainerInitializer is initialized,
        // to setup the ServerContainer for this web application context.
        JakartaWebSocketServletContainerInitializer.configure(socketHandler, (servletContext, container) -> {
            container.setDefaultMaxBinaryMessageBufferSize(128 * 1024);
            container.addEndpoint(GameEndpoint.class);
        });

        try {
            server.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}