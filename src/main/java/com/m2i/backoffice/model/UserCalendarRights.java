package com.m2i.backoffice.model;

import jakarta.persistence.*;

@Entity
public class UserCalendarRights {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "idCalendar")
    private Calendar calendar;

    @Column(nullable = false)
    private String rights;



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getRights() {
        return rights;
    }
    public void setRights(String rights) {
        this.rights = rights;
    }
}
