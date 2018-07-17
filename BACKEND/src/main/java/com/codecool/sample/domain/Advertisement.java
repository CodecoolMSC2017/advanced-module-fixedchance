package com.codecool.sample.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="advertisements", schema="public")
public class Advertisement extends AbstractModel {

    private Integer companyId;
    private String name;
    private String description;

    public Advertisement() {}

    // Getters
    public Integer getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
