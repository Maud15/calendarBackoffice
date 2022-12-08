package com.m2i.backoffice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCalendar")
    private Long id;

    private String name;

    @Column(nullable = false)
    private boolean isMainCalendar;

    @OneToMany(targetEntity = UserCalendarRights.class, mappedBy = "calendar")
    private List<UserCalendarRights> calendarUserRightsList;



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

    public boolean isMainCalendar() {
        return isMainCalendar;
    }
    public void setMainCalendar(boolean mainCalendar) {
        isMainCalendar = mainCalendar;
    }

    public List<UserCalendarRights> getCalendarUserRightsList() {
        return calendarUserRightsList;
    }
    public void setCalendarUserRightsList(List<UserCalendarRights> calendarUserRightsList) {
        this.calendarUserRightsList = calendarUserRightsList;
    }
}
