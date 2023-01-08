package com.m2i.backoffice.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

//    private String name;

    @Column(name = "is_main_calendar", nullable = false)
    private boolean isMainCalendar;

    @OneToMany(mappedBy = "calendar")
    private List<UserCalendarRights> calendarUserRightsList;

    @OneToMany
    @JoinColumn(referencedColumnName = "calendar_id")
    private List<Event> eventsList;

    public Calendar(){}
    public Calendar(Long id){
        this.id = id;
    }
    public Calendar(boolean isMainCalendar){
        this.isMainCalendar = isMainCalendar;
    }
    public Calendar(Long id,  boolean isMainCalendar){
        this.id = id;
        this.isMainCalendar = isMainCalendar;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public List<Event> getEventsList() {
        return eventsList;
    }
    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}
