package com.fxfinals.fxapp_finals.utility;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + Config.DB_HOST + ":" + Config.DB_PORT + "/" + Config.DB_NAME, Config.DB_USER, Config.DB_PASSWORD);
            System.out.println("Connection Success <3");
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void generateUser(String username, String password){
        String query = "INSERT INTO user(username, password) VALUES (?, ?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            ps.executeUpdate();
            System.out.println("New user created");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
