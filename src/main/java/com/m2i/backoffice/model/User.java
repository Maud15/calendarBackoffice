package com.m2i.backoffice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
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

    /*//Un utilisateur n'a qu'une ville associée
    //Une ville peut être associée à plusieurs utilisateurs
    @ManyToOne
    @JoinColumn(name="idCity")
    private City city; // todo : voir si on fait du fetch = EAGER ou LAZY -> et savoir la différence que ça fait*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
