package com.backend.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LobbyServlet extends HttpServlet {

    private String corsHeader = "Access-Control-Allow-Origin";
    private Logger logger = LoggerFactory.getLogger(LobbyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getPathInfo());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader(this.corsHeader, req.getHeader("origin"));

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