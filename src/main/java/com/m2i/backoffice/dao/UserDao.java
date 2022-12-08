package com.m2i.backoffice.dao;

import com.m2i.backoffice.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{


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
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }
}
