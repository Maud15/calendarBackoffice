package com.m2i.backoffice.service;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.City;
import com.m2i.backoffice.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static final UserDao USER_DAO = new UserDao();

    public Optional<User> get(Long id) {
        return USER_DAO.get(id, User.class);
    }

    public List<User> getAll() {
        return USER_DAO.getAll();
    }

    public User create(String pseudo, String email,String password, String firstname, String lastname, City city, boolean admin, boolean superAdmin) {
        User newUser = new User(pseudo, email, password, firstname, lastname, city, admin, superAdmin);
        return USER_DAO.create(newUser);
    }

    public boolean update(Long id, String pseudo, String email, String firstname, String lastname, City city,boolean admin,boolean superAdmin) {

        Optional<User> optUser = USER_DAO.get(id, User.class);
        if(optUser.isPresent()) {
            User user = optUser.get();
            user.setPseudo(pseudo);
            user.setEmail(email);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setCity(city);
            user.setAdmin(admin);
            user.setSuperAdmin(superAdmin);

            //TODO
            System.out.println("SI MODIFICATION SUR CHAMP SUPERADMIN VEFIFIER QUE L'ADMIN CONNECTE EST BIEN UN SUPERADMIN");
            /*if(currentUser.isSuperAdmin()) {
                String superAdmin = Boolean.parseBoolean(req.getParameter("superAdmin"));
            }*/
            return USER_DAO.update(user);
        }
        return false;
    }

    public boolean delete(Long id) {
        return USER_DAO.delete(id, User.class);
    }
}
