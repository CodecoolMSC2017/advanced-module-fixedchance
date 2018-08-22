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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Course> teacherCourses;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CourseReview> reviews;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<StudentAnswer> answers;

    /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Post> posts;*/

    @ElementCollection
    @CollectionTable(
            name = "authorities",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @Column(name = "authority")
    private List<String> authorities;

    @JsonInclude()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String confpassword;


    public User() {
    }

    // Getters
    public Set<StudentAnswer> getAnswers() {
        return answers;
    }

    public Set<CourseReview> getReviews() {
        return reviews;
    }

    public String getUsername() {
        return username;
    }

    /*public Set<Post> getPosts() {
        return posts;
    }*/

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public Set<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    // Setters
    public void setAnswers(Set<StudentAnswer> answers) {
        this.answers = answers;
    }

    public void setReviews(Set<CourseReview> reviews) {
        this.reviews = reviews;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }*/

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setTeacherCourses(Set<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
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
