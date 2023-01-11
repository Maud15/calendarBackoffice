package com.m2i.backoffice.model;

import jakarta.persistence.*;

@Entity (name = "user_calendar_rights")
public class UserCalendarRights {

    @EmbeddedId
    private UserCalendarRightsId userCalendarRightsId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("calendarId")
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Column(nullable = false)
    private String rights;

    public UserCalendarRights(){}

    public UserCalendarRights(UserCalendarRightsId userCalendarRightsId, User user, Calendar calendar, String rights) {
        this.userCalendarRightsId = userCalendarRightsId;
        this.rights = rights;
        this.calendar = calendar;
        this.user = user;
    }

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
