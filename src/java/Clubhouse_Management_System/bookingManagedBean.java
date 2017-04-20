package Clubhouse_Management_System;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.text.html.Option;

/**
 *
 * @author admin
 */
@Named(value = "bookingManagedBean")
@RequestScoped

public class bookingManagedBean implements Serializable {

    @EJB
    private bookingSessionBeanRemote bookingSessionBeanRemote;
    String service;
    String date;
    String time;
    String selected;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    

    public List<String> findService() {
        
        return bookingSessionBeanRemote.service();
    }

    public bookingSessionBeanRemote getBookingSessionBeanRemote() {
        return bookingSessionBeanRemote;
    }

    public void setBookingSessionBeanRemote(bookingSessionBeanRemote bookingSessionBeanRemote) {
        this.bookingSessionBeanRemote = bookingSessionBeanRemote;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String findByService() {
       
        return bookingSessionBeanRemote.findByService(selected);
    }

    public String findByTime(String date, String time) {
        return "";

    }
    
    public bookingManagedBean() {
//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error, please try again later..." + selected, null));
    }

}
