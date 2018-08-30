package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractModel implements Serializable {

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enabled;

    @ElementCollection
    @CollectionTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @Column(name = "authority")
    private List<String> authorities;

    public User() {
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAll(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();

    }

    // Methods
    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + "\'" +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
