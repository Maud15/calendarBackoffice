package com.m2i.backoffice.model;

import jakarta.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idCity")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pays;

    /*@OneToMany(targetEntity = User.class, mappedBy = "city")
    private List<User> usersList;*/


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }
}
