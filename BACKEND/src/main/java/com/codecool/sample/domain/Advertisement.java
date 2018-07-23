package com.codecool.sample.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="advertisements", schema="public")
public class Advertisement extends AbstractModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
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
}
