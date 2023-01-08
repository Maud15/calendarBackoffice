package com.m2i.backoffice.service;

import com.m2i.backoffice.dao.RoleDao;
import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.*;
import com.m2i.backoffice.service.exception.InvalidPasswordException;
import com.m2i.backoffice.service.exception.UnknownUserException;
import com.m2i.backoffice.service.exception.UnknownValueException;
import com.m2i.backoffice.service.exception.UserAlreadyExistsException;

import java.util.*;

public class UserService {

    private static final UserDao USER_DAO = new UserDao();

    public Optional<User> get(Long id) {
        return USER_DAO.get(id, User.class);
    }

    public List<User> getAll() {
        return USER_DAO.getAll();
    }

    public Optional<User> create(String pseudo, String email, String password, String firstname, String lastname, String cityName, String roleName /*List<Role> roleList, List<UserCalendarRights> calendarRightsList*/) throws UserAlreadyExistsException, UnknownValueException, InvalidPasswordException {
        if(isValidPassword(password)) {
            if(USER_DAO.getByPseudo(pseudo).isPresent()) {
                throw new UserAlreadyExistsException("pseudo",pseudo);
            } else if(USER_DAO.getByEmail(email).isPresent()) {
                throw new UserAlreadyExistsException("email",email);
            } else {
                Role role = getRoleByName(roleName);
                User newUser = new User(pseudo, email, password, firstname, lastname, null, List.of(role)/* calendarRightsList*/);
                return Optional.of(USER_DAO.create(newUser));
            }
        } else {
            throw new InvalidPasswordException();
        }
    }

    public boolean update(Long id, String pseudo, String email, String firstname, String lastname, String cityName, String roleName) {
        try {
            Optional<User> optUser = USER_DAO.get(id, User.class);
            if(optUser.isPresent()) {
                User user = optUser.get();
                user.setPseudo(pseudo);
                user.setEmail(email);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setCity(null);

                List<Role> newRoleList = new ArrayList<>();
                newRoleList.add(getRoleByName(roleName));

                boolean userUpdatedIsSuperAdmin = user.getRoleList().stream()
                        .filter(role -> role.getName() == RoleEnum.ROLE_SUPER_ADMIN).toList().size() > 0;
                boolean newRoleIsSuperAdmin = Objects.equals(roleName, RoleEnum.ROLE_SUPER_ADMIN.name());
                if (userUpdatedIsSuperAdmin || newRoleIsSuperAdmin) {
                    //TODO
                    /*if (currentUser.isSuperAdmin()) {
                        user.setRoleList(newRoleList);
                    } else {
                        throw new UnauthorizedException("Only super-administrators are allowed to set or remove super-administrators role");
                    }*/
                } else {
                    user.setRoleList(newRoleList);
                }
                return USER_DAO.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        try {
            Optional<User> optUser = USER_DAO.get(id, User.class);
            if (optUser.isPresent()) {
                User user = optUser.get();
                boolean userDeletedIsSuperAdmin = user.getRoleList().stream()
                        .filter(role -> role.getName() == RoleEnum.ROLE_SUPER_ADMIN).toList().size() > 0;
                if (userDeletedIsSuperAdmin) {
                    //TODO
                    /*if (currentUser.isSuperAdmin()) {
                        return USER_DAO.delete(id, User.class);
                    } else {
                        throw new UnauthorizedException("Only super-administrators are allowed to delete super-administrators");
                    }*/
                } else {
                    return USER_DAO.delete(id, User.class);
                }
            } else {
                throw new UnknownUserException();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Role getRoleByName(String roleName) throws UnknownValueException {
        Optional<Role> optRole = new RoleDao().getByName(roleName);
        if(optRole.isPresent()) {
            return optRole.get();
        }
        throw new UnknownValueException("Role", roleName);
    }

    private boolean isValidPassword(String password) {
        int uppercaseCounter =0;
        int lowercaseCounter =0;
        int digitCounter =0;
        int specialCounter =0;
        for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c))
                uppercaseCounter++;
            else if(Character.isLowerCase(c))
                lowercaseCounter++;
            else if(Character.isDigit(c))
                digitCounter++;
            if(c>=33&&c<=46||c==64){
                specialCounter++;
            }
        }
        return password.length() > 7 && uppercaseCounter > 0 && lowercaseCounter > 0 && digitCounter > 0;
    }
}
