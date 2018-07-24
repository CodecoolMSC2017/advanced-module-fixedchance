package com.codecool.sample.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="advertisements", schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Advertisement extends AbstractModel {

    @JsonBackReference(value = "advertisements.company")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @NotNull
    private Company company;

    private String name;
    private String description;

    public Advertisement() {}

    // Getters
    public Company getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCompany(Company company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Methods
    @Override
    public String toString() {
        return "Advertisement{" +
                "company=" + company +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
