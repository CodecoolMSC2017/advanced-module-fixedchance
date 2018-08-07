package com.codecool.fixedchance.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserDTO extends AbstractModel {

    private String username;
    private Integer experience;
    private Date registrationDate;
    private String role;

    public UserDTO() {}

    // Getters
    public String getUserName() {
        return username;
    }

    public Integer getExperience() {
        return experience;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUserName(String username) {
        this.username = username;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Methods
    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + username + '\'' +
                ", experience=" + experience +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}
