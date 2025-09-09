package com.backend.webserver.servlets;

import com.backend.webserver.ws_endpoints.CreateLobby;
import com.backend.webserver.ws_endpoints.CreateUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.server.ServerContainer;

public class WebSocketInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            // Retrieve the ServerContainer from the ServletContext attributes.
            ServerContainer container = (ServerContainer) getServletContext()
                    .getAttribute(ServerContainer.class.getName());

            // Configure the ServerContainer.
            container.setDefaultMaxTextMessageBufferSize(128 * 1024);

            // Simple registration of your WebSocket endpoints.
            container.addEndpoint(CreateUser.class);
            container.addEndpoint(CreateLobby.class);

        } catch (DeploymentException x) {
            throw new ServletException(x);
        }
    }
}