/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clubhouse_Management_System;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author admin
 */
@Named(value = "contactManagedBean")
@SessionScoped
public class contactManagedBean implements Serializable {
    @EJB
    private contactSessionBeanRemote contactSessionBeanRemote;
    String name;
    String email;
    String message;
    public contactSessionBeanRemote getContactSessionBeanRemote() {
        return contactSessionBeanRemote;
    }

    public void setContactSessionBeanRemote(contactSessionBeanRemote contactSessionBeanRemote) {
        this.contactSessionBeanRemote = contactSessionBeanRemote;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public contactManagedBean() {
    }
    
}
