package com.m2i.backoffice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserCalendarRightsId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name="calendar_id")
    private Long calendarId;

    public UserCalendarRightsId(){}

    public UserCalendarRightsId(Long userId, Long calendarId) {
        this.userId = userId;
        this.calendarId = calendarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }
}
