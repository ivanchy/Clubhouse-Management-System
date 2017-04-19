package Clubhouse_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class LoginDAO {

    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = ds.getConnection();
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        }
        return false;
    }

    public String type() {
        HttpSession session = SessionUtils.getSession();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = ds.getConnection();
            ps = con.prepareStatement("Select type from Users where uname = ? ");
            ps.setString(1, session.getAttribute("username").toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                con.close();
                return rs.getString("type");
            }
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return "false";
        }
        return "false";
    }
}
