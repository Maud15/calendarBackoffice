package com.m2i.backoffice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "start-date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end-date", nullable = false)
    private LocalDate endDate;

    @Column (name = "is_full_day", nullable = false)
    private boolean isFullDay;

    @Column (nullable = false)
    private Long idCalendar;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
