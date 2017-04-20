/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clubhouse_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author johnleung
 */
@Stateless
public class FacilitySessionBean {

    @Resource(name = "cms")
    private DataSource cms;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public String display() {
        String name;
        String output = "";
        
        try {
            Context ctx = new InitialContext();
            cms = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = cms.getConnection();
            ps = con.prepareStatement("Select * from facility");

            rs = ps.executeQuery();
            
            while (rs.next()) {
                //Retrieve by column name
                name = rs.getString("name");
                
                output += "<tr><td>" + rs.getString("name") + "</td></tr>";
                
            }
            con.close();
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return output;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
