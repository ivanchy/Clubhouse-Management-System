/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clubhouse_Management_System;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author johnleung
 */
@Named(value = "facilityManagedBean")
@SessionScoped
public class FacilityManagedBean implements Serializable {

    @Resource(name = "cms")
    private DataSource cms;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<String> fac = new ArrayList<>();
    private String name;
    
    /**
     * Creates a new instance of FacilityManagedBean
     */
    public FacilityManagedBean() {
        
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ArrayList<String> getFac() {
        return display();
    }

    public void setFac(ArrayList<String> fac) {
        this.fac = fac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> display() {
        
        try {
            Context ctx = new InitialContext();
            cms = (DataSource) ctx.lookup("java:app/jdbc/cms");
            con = cms.getConnection();
            ps = con.prepareStatement("Select * from facility");

            rs = ps.executeQuery();
            fac.clear();
            while (rs.next()) {
                //Retrieve by column name
                name = rs.getString("name");

                fac.add(name);
                System.out.println(name);
            }
            con.close();
        } catch (Exception ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
        return fac;
    }
}
