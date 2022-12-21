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

    @Column(name="isAdmin", nullable = false)
    private boolean admin;

    @Column(name="isSuperAdmin", nullable = false)
    private boolean superAdmin;

    @ManyToOne
    @JoinColumn(name="idCity")
    private City city;

    @OneToMany(targetEntity = UserCalendarRights.class, mappedBy = "user")
    private List<UserCalendarRights> calendarRightsList;


    public User() {
    }

    public User(String pseudo, String email, String password, String firstname, String lastname, City city, boolean admin, boolean superAdmin) {
        this(null,pseudo,email,password,firstname,lastname,city,admin,superAdmin, null);
    }

/*    public User(Long id, String pseudo, String email, String firstname, String lastname, City city, boolean admin, boolean superAdmin) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.superAdmin = superAdmin;
        if(superAdmin) {
            this.admin = true;
        } else {
            this.admin = admin;
        }
    }*/

    public User(Long id, String pseudo, String email, String password, String firstname, String lastname, City city, boolean admin, boolean superAdmin, List<UserCalendarRights> calendarRightsList) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.superAdmin = superAdmin;
        if(superAdmin) {
            this.admin = true;
        } else {
            this.admin = admin;
        }
        this.calendarRightsList = calendarRightsList;
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
        return this.admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }
    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<UserCalendarRights> getCalendarRightsList() {
        return calendarRightsList;
    }
    public void setCalendarRightsList(List<UserCalendarRights> calendarRightsList) {
        this.calendarRightsList = calendarRightsList;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
}
