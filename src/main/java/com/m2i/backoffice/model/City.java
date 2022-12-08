package com.m2i.backoffice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "idCity")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pays;



}
