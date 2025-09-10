package com.backend.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.backend.database.HibernateUtil;
import com.backend.model.Lobby;
import com.backend.model.Player;
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

    public void respondHttp(HttpServletRequest request, HttpServletResponse response, String message) {
        response.setHeader(this.corsHeader, request.getHeader("origin"));
        response.setContentType("application.json");
        response.setCharacterEncoding("UTF-8");

        try {
            PrintWriter writer = response.getWriter();
            writer.println(message);

        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(Servlet.class);
            logger.error("Response error", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // route

        // route

        String jsonString = "{\"name\":\"John Doe\"}";
        respondHttp(req, resp, jsonString);

        try {
            SessionFactory sessionFactory = HibernateUtil.getInstance();

            Player player = objectMapper.readValue(req.getReader(), Player.class);
            player.setUUID();

            Lobby lobby = new Lobby(UUID.randomUUID());

            sessionFactory.inTransaction(session -> {
                session.persist(lobby);
                session.persist(player);
                session.flush();
            });

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}