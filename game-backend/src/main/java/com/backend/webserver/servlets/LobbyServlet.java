package com.backend.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.backend.database.Database;
import com.backend.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LobbyServlet extends HttpServlet {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String corsHeader = "Access-Control-Allow-Origin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter response = resp.getWriter();
        response.println("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));
        resp.setContentType("application.json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Player player = objectMapper.readValue(req.getReader(), Player.class);
            System.out.println(player.getUsername());
            Database db = new Database();
            System.out.println(db.getCatalog());
            System.out.println(db.getCatalog());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}