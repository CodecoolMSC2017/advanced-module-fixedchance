package com.codecool.sample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users", schema="public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer experience;
    private String email, firstName, lastName, password, description;
    private Date birthDate, registrationDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonInclude()
    @Transient
    private String confpassword;

    public User() {
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public String getConfpassword() {
        return confpassword;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }

    // Methods

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", experience=" + experience +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", description='" + description + '\'' +
                ", confpassword='" + confpassword + '\'' +
                '}';
    }
}
