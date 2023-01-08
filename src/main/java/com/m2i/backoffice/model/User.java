package com.m2i.backoffice.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pseudo;

    @Column(nullable = false)
    private String password;

    private String firstname;
    private String lastname;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    //On le met en EAGER pour ne pas avoir de problème avec le getAuthorities
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;

    @OneToMany(targetEntity = UserCalendarRights.class, mappedBy = "user")
    private List<UserCalendarRights> calendarRightsList;


    public User() {
    }
    public User(Long id){
        this.id = id;
    }

    public User(String pseudo, String email, String password, String firstname, String lastname, City city, List<Role> roleList) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.roleList = roleList;
    }
    public User(Long id, String pseudo, String email, String password, String firstname, String lastname, City city, List<Role> roleList, List<UserCalendarRights> calendarRightsList) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.roleList = roleList;
        this.calendarRightsList = calendarRightsList;
    }

    public boolean isAdminOrSuperAdmin() {
        return this.getRoleList().stream()
                .anyMatch(role -> role.getName() == RoleEnum.ROLE_SUPER_ADMIN || role.getName() == RoleEnum.ROLE_ADMIN);
    }
    public boolean isSuperAdmin() {
        return this.getRoleList().stream().anyMatch(role -> role.getName() == RoleEnum.ROLE_SUPER_ADMIN);
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

    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
