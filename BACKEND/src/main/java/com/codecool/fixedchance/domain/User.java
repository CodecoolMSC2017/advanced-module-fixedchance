package com.codecool.fixedchance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractModel implements Serializable {

    private Integer experience = 0;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String description;
    private Date birthDate;
    private boolean enabled;
    private Date registrationDate = new Date();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Course> teacherCourses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Schedule> schedules = new HashSet<>();

    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Course studentCourse;*/

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "course_student",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<User> students = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @Column(name = "authority")
    private List<String> authorities;

    @JsonInclude()
    @Transient
    private String confpassword;

    public User() {
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Set<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public Set<User> getStudents() {
        return students;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getUserName() {
        return username;
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

    public String getDescription() {
        return description;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setTeacherCourses(Set<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setUserName(String userName) {
        this.username = userName;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAll(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
    }

    // Methods
    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + "\'" +
                ", experience='" + experience + "\'" +
                ", email='" + email + '\'' +
                ", userName='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", birthDate=" + birthDate +
                ", enabled=" + enabled +
                ", registrationDate=" + registrationDate +
                ", confpassword='" + confpassword + '\'' +
                '}';
    }
}