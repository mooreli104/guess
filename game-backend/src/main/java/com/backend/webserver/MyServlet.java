package com.backend.webserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.json.JsonWriter;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter response = resp.getWriter();

        response.println("{\"my_key\": \"my_value\"}");

        // JsonWriter json = Json.createWriter(response);
        // JsonString jsonObject = Json.createValue("Hello");
        // json.write(jsonObject);
        // json.close();

    }

    // @Override
    // public void init() throws ServletException {
    // try {
    // // Retrieve the ServerContainer from the ServletContext attributes.
    // ServerContainer container = (ServerContainer) getServletContext()
    // .getAttribute(ServerContainer.class.getName());

    // // Configure the ServerContainer.
    // container.setDefaultMaxTextMessageBufferSize(128 * 1024);

    // // Simple registration of your WebSocket endpoints.
    // // container.addEndpoint(MyEndpoint.class);

    // // Advanced registration of your WebSocket endpoints.
    // container.addEndpoint(
    // ServerEndpointConfig.Builder.create(MyEndpoint.class, "/")
    // .subprotocols(List.of("http"))
    // .build());

    // } catch (DeploymentException x) {
    // throw new ServletException(x);
    // }
    // }
}