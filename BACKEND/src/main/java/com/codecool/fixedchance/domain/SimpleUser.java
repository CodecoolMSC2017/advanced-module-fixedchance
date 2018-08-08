package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "simple_users", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SimpleUser extends AbstractModel implements Serializable {

    private Integer experience = 0;
    private String email;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    private String firstName;
    private String lastName;
    private String description;
    private Date birthDate;
    private Date registrationDate = new Date();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Course> teacherCourses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CourseReview> reviews;

    @JsonInclude()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String confpassword;

    /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Post> posts;*/



    public SimpleUser() {
    }

    // Getters & Setters
    public Set<CourseReview> getReviews() {
        return reviews;
    }

    /*public Set<Post> getPosts() {
        return posts;
    }*/

    public void setReviews(Set<CourseReview> reviews) {
        this.reviews = reviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(Set<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /*public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }*/

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    // Methods
    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + "\'" +
                ", experience='" + experience + "\'" +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", birthDate=" + birthDate +
                ", registrationDate=" + registrationDate +
                ", confpassword='" + confpassword + '\'' +
                '}';
    }
}

