package com.m2i.backoffice.dao;

import com.m2i.backoffice.model.Calendar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class CalendarDao implements Dao<Calendar> {

    @Override
    public List<Calendar> getAll() {
        return null;
    }

    @Override
    public Optional<Calendar> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Optional<Calendar> optCalendar = Optional.of(em.find(Calendar.class, id));
            optCalendar.ifPresent(em::remove);
            et.commit();
        } catch(Exception e) {
            System.out.println("Impossible de supprimer l'élément choisi");
            e.printStackTrace();
            if (et.isActive()) { et.rollback(); }
        } finally {
            em.close();
        }
    }
}
