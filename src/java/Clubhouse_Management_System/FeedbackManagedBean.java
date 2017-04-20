/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clubhouse_Management_System;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author johnleung
 */
@Named(value = "feedbackManagedBean")
@SessionScoped
public class FeedbackManagedBean implements Serializable {

    @Resource(name = "cms")
    private DataSource cms;
    private String name;
    private String message;
    private int size = 0;
    private int count = 0;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Creates a new instance of MessageManagedBean
     */
    public FeedbackManagedBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String display() {

        String output = "";

        try {
            Context ctx = new InitialContext();
            cms = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = cms.getConnection();
            ps = con.prepareStatement("Select name, message from feedback");

            rs = ps.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                name = rs.getString("name");
                message = rs.getString("message");

                output += "<tr><td>" + name + "</td><td>" + message + "</td></tr>";
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return output;
    }

}
