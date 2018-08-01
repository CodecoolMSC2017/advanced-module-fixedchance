package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies", schema = "public")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends AbstractModel implements Serializable {

    private String name;
    private String username;
    private Date registrationDate = new Date();
    private String email;
    private String password;
    private boolean active;
    private String subscription;
    private Date paymentDate;
    private String description;
    private boolean enabled;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    private Set<Advertisement> ads = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "company_authorities",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @Column(name = "authority")
    private List<String> authorities;

    public Company() {
    }

    // Getters
    public Date getPaymentDate() {
        return paymentDate;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }

    public Set<Advertisement> getAds() {
        return ads;
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
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAds(Set<Advertisement> ads) {
        this.ads = ads;
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
                ", username='" + username + '\'' +
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
