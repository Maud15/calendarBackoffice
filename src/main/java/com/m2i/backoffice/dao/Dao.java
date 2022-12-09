package com.m2i.backoffice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll();

    default Optional<T> get(Long id, Class<T> tClass) {
        Optional<T> optionalT = Optional.empty();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            optionalT = Optional.of(em.find(tClass, id));
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return optionalT;
    }

    default T create(T t) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(et.isActive()) {et.rollback();}
        } finally {
            em.close();
        }
        return t;
    }

    default boolean update(T t) {
        boolean isSuccess = false;
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(t);
            et.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(et.isActive()) {et.rollback();}
        } finally {
            em.close();
        }
        return isSuccess;
    }

    default boolean delete(Long id, Class<T> tClass) {
        boolean isSuccess = false;
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Optional<T> optElement = Optional.of(em.find(tClass, id));
            optElement.ifPresent(em::remove);
            et.commit();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(et.isActive()) {et.rollback();}
        } finally {
            em.close();
        }
        return isSuccess;
    }

}
