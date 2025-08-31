package com.backend.webserver;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;

public class MyServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            // Retrieve the ServerContainer from the ServletContext attributes.
            ServerContainer container = (ServerContainer) getServletContext()
                    .getAttribute(ServerContainer.class.getName());

            // Configure the ServerContainer.
            container.setDefaultMaxTextMessageBufferSize(128 * 1024);

            // Simple registration of your WebSocket endpoints.
            // container.addEndpoint(MyEndpoint.class);

            // Advanced registration of your WebSocket endpoints.
            container.addEndpoint(
                    ServerEndpointConfig.Builder.create(MyEndpoint.class, "/")
                            .subprotocols(List.of("http"))
                            .build());
        } catch (DeploymentException x) {
            throw new ServletException(x);
        }
    }
}