package com.m2i.backoffice.dao;

import com.m2i.backoffice.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            userList = em.createQuery("select u from User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( em != null ) em.close();
        }
        return userList;
    }

    @Override
    public Optional<User> get(Long id) {
        Optional<User> optUser = Optional.empty();
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            optUser = Optional.of(em.find(User.class, id));
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return optUser;
    }

    /*@Override
    public void update(User user) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(user);
            et.commit();
        } catch(Exception e) {
            if(et.isActive()) {et.rollback();}
            e.printStackTrace();
        } finally {
            em.close();
        }
    }*/

    @Override
    public void delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Optional<User> optCustomer = Optional.of(em.find(User.class, id));
            optCustomer.ifPresent(em::remove);
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
