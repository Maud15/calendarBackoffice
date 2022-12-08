package com.m2i.backoffice.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pseudo;

    @Column(nullable = false)
    private String password; // todo : gérer le hashage du password pour ne pas le stocker en clair

    private String firstname;
    private String lastname;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToMany(targetEntity = UserCalendarRights.class, mappedBy = "user")
    private List<UserCalendarRights> userCalendarRightsList;

    @ManyToOne
    @JoinColumn(name="idCity")
    private City city;

    public User() {
    }

    public User(String email, String pseudo, String password, boolean isAdmin) {
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<UserCalendarRights> getUserCalendarRightsList() {
        return userCalendarRightsList;
    }
    public void setUserCalendarRightsList(List<UserCalendarRights> userCalendarRightsList) {
        this.userCalendarRightsList = userCalendarRightsList;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
}
