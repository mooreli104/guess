package com.backend.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.service.GameService;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String corsHeader = "Access-Control-Allow-Origin";
    private Logger logger = LoggerFactory.getLogger(GameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        String jsonString = "{\"name\":\"John Doe\"}";

        switch (req.getContextPath()) {
            case "/connect":
                Reader player = req.getReader();
                connectToLobby(player);
                sendResponse(resp, jsonString);
                break;

            default:
                break;
        }

    }

    public void connectToLobby(Reader input) {
        try {
            Player player = objectMapper.readValue(input, Player.class);
            Lobby lobby = new Lobby();

            GameService gameService = GameService.getInstance();
            gameService.createLobby(lobby, player);

        } catch (Exception e) {
            this.logger.error("Connection error", e);
        }
    }

    public void sendResponse(HttpServletResponse response, String message) {

        try {
            PrintWriter writer = response.getWriter();
            writer.println(message);

        } catch (Exception e) {
            this.logger.error("Response error", e);
        }

    }

}