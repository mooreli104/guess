package com.backend.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.service.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(PlayerServlet.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> object = AnimeService.getInstance().getAnime();
        String json = objectMapper.writeValueAsString(object);

        System.out.println(object.keySet());

        PrintWriter writer = resp.getWriter();
        writer.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
