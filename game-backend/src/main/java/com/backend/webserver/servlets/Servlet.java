package com.backend.webserver.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.hibernate.tool.schema.Action;

import com.backend.database.Database;
import com.backend.model.Lobby;
import com.backend.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String USER = "moore";
    private final String PASSWORD = "sachisroom";
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
            SessionFactory sessionFactory = new HibernatePersistenceConfiguration("lobby")
                    .managedClass(Lobby.class)
                    // PostgreSQL
                    .jdbcUrl("jdbc:postgresql://localhost/gameDB")
                    // Credentials
                    .jdbcCredentials(this.USER, this.PASSWORD)
                    // Automatic schema export
                    .schemaToolingAction(Action.CREATE_ONLY)
                    // SQL statement logging
                    .showSql(true, true, true)
                    // Create a new SessionFactory
                    .createEntityManagerFactory();

            Player player = objectMapper.readValue(req.getReader(), Player.class);
            System.out.println(player.getUsername());
            player.setUUID();
            Lobby lobby = new Lobby();
            lobby.setLobbyID();

            Session session = sessionFactory.openSession();
            session.persist(lobby);

            String jsonString = "{\"name\":\"John Doe\"}";

            PrintWriter response = resp.getWriter();
            response.println(jsonString);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}