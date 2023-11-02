package org.example;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private Connection connection;


    private Database(){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            flywayMigration();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public static Database getInstance(){
        return INSTANCE;

    }
    public Connection getConnection(){
        return connection;
    }

    private void flywayMigration(){
        Flyway flyway = Flyway.configure().dataSource(URL,USER,PASSWORD).load();
        flyway.migrate();
    }
}
