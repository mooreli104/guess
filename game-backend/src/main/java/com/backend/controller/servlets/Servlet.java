package com.backend.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.backend.model.Lobby;
import com.backend.model.Player;
import com.backend.service.LobbyService;
import com.backend.service.PlayerService;
import com.backend.util.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Servlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String corsHeader = "Access-Control-Allow-Origin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        resp.setContentType("application.json");
        resp.setCharacterEncoding("UTF-8");

        String jsonString = "{\"name\":\"John Doe\"}";

        try {
            Player player = objectMapper.readValue(req.getReader(), Player.class);
            player.setUUID();

            Lobby lobby = new Lobby(UUID.randomUUID());

            LobbyService lobbyService = LobbyService.getInstance();
            PlayerService playerService = PlayerService.getInstance();

            lobbyService.registerLobby(lobby);
            player.setLobby(lobby);

            playerService.registerPlayer(player);
            respondHttp(resp, jsonString);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void respondHttp(HttpServletResponse response, String message) {

        try {
            PrintWriter writer = response.getWriter();
            writer.println(message);

        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(Servlet.class);
            logger.error("Response error", e);
        }

    }

}