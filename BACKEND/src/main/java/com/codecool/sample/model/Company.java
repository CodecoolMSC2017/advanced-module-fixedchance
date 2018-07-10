package com.codecool.sample.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="companies", schema="public")
public class Company extends AbstractModel {

    private String name;
    private Date registrationDate = new Date();
    private String email;
    private String password;
    private boolean active;
    private String subscription;
    private String description;

    public Company() {
    }

    // Getters
    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getSubscription() {
        return subscription;
    }

    public String getDescription() {
        return description;
    }


    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Methods

    @Override
    public String toString() {
        return "Company{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", subscription='" + subscription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
