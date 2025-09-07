package com.backend.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.backend.database.HibernateUtil;
import com.backend.model.Lobby;
import com.backend.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String corsHeader = "Access-Control-Allow-Origin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String jsonString = "{\"name\":\"John Doe\"}";

        PrintWriter response = resp.getWriter();
        response.println(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        resp.setContentType("application.json");
        resp.setCharacterEncoding("UTF-8");

        try {
            SessionFactory sessionFactory = HibernateUtil.getInstance();

            Player player = objectMapper.readValue(req.getReader(), Player.class);
            player.setUUID();

            Lobby lobby = new Lobby(UUID.randomUUID());

            sessionFactory.inTransaction(session -> {
                session.persist(lobby);
                session.flush();
            });

            PrintWriter response = resp.getWriter();
            String jsonString = "{\"name\":\"John Doe\"}";
            response.println(jsonString);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}