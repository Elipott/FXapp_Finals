package com.fxfinals.fxapp_finals.controllers;

import com.fxfinals.fxapp_finals.utility.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void handlelogin(ActionEvent event) {
        String username = username.getText();
        String password = password.getText();
        // Validation

        if(authenticateUser(username, password)){
            //Opening Home Window
        }
    }

    private boolean authenticateUser(String username, String password){
        Database db = new Database();
        String query = "SELECT username, password FROM users WHERE username = ?;";
        try{
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String dbPassword = rs.getString("password");
                return BCrypt.checkpw(password, dbPassword);
            }
            ps.executeUpdate();
            System.out.println("New user created");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

}
