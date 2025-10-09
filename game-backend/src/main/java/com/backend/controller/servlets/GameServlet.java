package com.backend.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import com.backend.model.Anime;
import com.backend.service.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> object = AnimeService.getInstance().getAnime();

        Map<String, Object> object2 = ((Map<String, Object>) ((ArrayList) object.get("data")).get(0));
        Map<String, Object> object3 = (Map<String, Object>) ((Map<String, Object>) object2.get("node"));
        String jsonString = objectMapper.writeValueAsString(object3);
        Anime anime = objectMapper.readValue(jsonString, Anime.class);

        PrintWriter writer = resp.getWriter();
        writer.println("{\"url\":\"" + anime.getmain_picture().getLarge() + "\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
