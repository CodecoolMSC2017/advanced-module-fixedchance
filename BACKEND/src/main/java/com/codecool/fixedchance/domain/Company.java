package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "companies", schema = "public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends AbstractModel implements Serializable {

    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String email;
    private boolean active;
    private String subscription;
    private Date registrationDate = new Date();
    private Date paymentDate;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private List<Advertisement> ads = new ArrayList<>();

    public Company() {
    }

    // Getters
    public User getUser() {
        return user;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public List<Advertisement> getAds() {
        return ads;
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
    public void setUser(User user) {
        this.user = user;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAds(List<Advertisement> ads) {
        this.ads = ads;
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
    }

    //Methods
    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subscription='" + subscription + '\'' +
                '}';
    }
}
