package com.backend.util;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.hibernate.tool.schema.Action;

import com.backend.model.Lobby;
import com.backend.model.Player;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();
    private static final String USER = "moore";
    private static final String PASSWORD = "sachisroom";

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new HibernatePersistenceConfiguration("lobby")
                    .managedClass(Lobby.class)
                    .managedClass(Player.class)
                    // PostgreSQL
                    .jdbcUrl("jdbc:postgresql://localhost/gameDB")
                    // Credentials
                    .jdbcCredentials(USER, PASSWORD)
                    // Automatic schema export
                    .schemaToolingAction(Action.CREATE)
                    // SQL statement logging
                    .showSql(true, true, true)
                    // Create a new SessionFactory
                    .createEntityManagerFactory();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getInstance() {
        if (HibernateUtil.sessionFactory == null) {
            HibernateUtil.buildSessionFactory();
        }
        return HibernateUtil.sessionFactory;
    }

}
