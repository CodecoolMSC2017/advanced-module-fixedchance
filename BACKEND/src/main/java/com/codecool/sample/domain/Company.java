package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends AbstractModel {

    private String name;
    private String username;
    private Date registrationDate = new Date();
    private String email;
    private String password;
    private boolean active;
    private String subscription;
    private String description;
    private boolean enabled;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private Set<Advertisement> ads = new HashSet<>();

    public Company() {
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public Set<Advertisement> getAds() {
        return ads;
    }

    public String getUserName() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

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
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAds(Set<Advertisement> ads) {
        this.ads = ads;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public void setAll(Company company) {
        this.name = company.getName();
        this.description = company.getDescription();
        this.password = company.getPassword();
    }

    //Methods
    @Override
    public String toString() {
        return "Company{" +
                "id='" + super.getId() +
                "name='" + name + '\'' +
                ", userName='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", subscription='" + subscription + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
