package com.m2i.backoffice.service;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.User;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User> {

    private static final UserDao USER_DAO = new UserDao();

    @Override
    public Optional<User> get(Long id) {
        return USER_DAO.get(id, User.class);
    }

    @Override
    public List<User> getAll() {
        return USER_DAO.getAll();
    }

    @Override
    public User create(User user) {
        return USER_DAO.create(user);
    }

    @Override
    public boolean update(User user) {
        return USER_DAO.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return USER_DAO.delete(id, User.class);
    }
}
