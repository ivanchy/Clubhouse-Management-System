/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clubhouse_Management_System;

import java.io.PrintWriter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    private String name;
    private String message;
    private int size = 0;
    private int count = 0;
    private String[] feedback;
    private ArrayList<String> fbs = new ArrayList<>();

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

    public ArrayList<String> getFbs() {
        return process();
    }

    public void setFbs(ArrayList<String> fbs) {
        this.fbs = fbs;
    }

    public ArrayList<String> process() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = ds.getConnection();
            ps = con.prepareStatement("Select name, message from feedback");

            rs = ps.executeQuery();
            fbs.clear();
            while (rs.next()) {
                //Retrieve by column name
                name = rs.getString("name");
                message = rs.getString("message");

                fbs.add(name + ": " + message);
//                fbs.add(name);
//                fbs.add(message);
                
            }
            con.close();
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return fbs;
    }

}
