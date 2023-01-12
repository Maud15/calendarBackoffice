package com.m2i.backoffice.service;

import com.m2i.backoffice.dao.*;
import com.m2i.backoffice.model.*;
import com.m2i.backoffice.model.Calendar;
import com.m2i.backoffice.service.exception.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.*;

public class UserService {

    private static final UserDao USER_DAO = new UserDao();
    private static final CalendarDao CALENDAR_DAO = new CalendarDao();
    private static final UserCalendarRightsDao USER_CAL_RIGHTS_DAO = new UserCalendarRightsDao();

    public Optional<User> get(Long id) {
        return USER_DAO.get(id, User.class);
    }

    public List<User> getAll() {
        return USER_DAO.getAll();
    }

    public Optional<User> create(String pseudo, String email, String password, String firstname, String lastname, String cityName, String roleName,boolean bypassPasswordValidity) throws Exception {
        String errorType;
        String errorData = "";
        User newUser = null;
        if(!isValidPseudo(pseudo)) {
            errorType = "invalidPseudo";
            errorData = pseudo;
        } else if(!isValidEmail(email)) {
            errorType = "invalidEmail";
            errorData = email;
        } else if(!isValidPassword(password, bypassPasswordValidity)) {
            errorType = "invalidPassword";
        } else {
            if(USER_DAO.getByPseudo(pseudo).isPresent()) {
                errorType = "pseudo";
                errorData = pseudo;
//                    throw new UserAlreadyExistsException("pseudo",pseudo);
            } else if(USER_DAO.getByEmail(email).isPresent()) {
                errorType = "email";
                errorData = email;
//                    throw new UserAlreadyExistsException("email",email);
            } else {
                Role role = getRoleByName(roleName);
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                EntityManager em = Dao.createEntityManager();
                EntityTransaction et = em.getTransaction();
                try {
                    et.begin();
                    newUser = USER_DAO.createInTransaction(em, new User(pseudo, email, hashedPassword, firstname, lastname, null, List.of(role)));
                    Calendar newCalendar = CALENDAR_DAO.createInTransaction(em, new Calendar(true));
                    UserCalendarRightsId userCalendarRightsId = new UserCalendarRightsId(newUser.getId(), newCalendar.getId());
                    USER_CAL_RIGHTS_DAO.createInTransaction(em, new UserCalendarRights(userCalendarRightsId, newUser, newCalendar, RightsEnum.OWNER.name()));
                    et.commit();
                } catch(Exception e) {
                    e.printStackTrace();
                    if(et.isActive()) {et.rollback();}
                } finally {
                    em.close();
                }
                return Optional.of(newUser);
            }
        }
        throw new UserCreationException(errorType, errorData);
    }

    public boolean update(String connectedUserRole, Long id, String pseudo, String email, String firstname, String lastname, String cityName, String roleName) {
        try {
            Optional<User> optUser = USER_DAO.get(id, User.class);
            if(optUser.isPresent()) {
                User user = optUser.get();
                user.setPseudo(pseudo);
                user.setEmail(email);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setCity(null);

                // Control role change
                List<Role> userUpdatedCurrentRoleList = user.getRoleList();
                List<Role> userUpdatedNewRoleList = new ArrayList<>();
                userUpdatedNewRoleList.add(getRoleByName(roleName));
                if(!userUpdatedNewRoleList.equals(userUpdatedCurrentRoleList)) {
                    boolean newRoleIsSuperAdmin = Objects.equals(roleName, RoleEnum.ROLE_SUPER_ADMIN.name());
                    if ((user.isSuperAdmin() || newRoleIsSuperAdmin) && !isSuperAdmin(connectedUserRole)) {
                        throw new UnauthorizedException("Only super-administrators are allowed to set or remove super-administrators role");
                    } else {
                        user.setRoleList(userUpdatedNewRoleList);
                    }
                }
                return USER_DAO.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String connectedUserRole, Long id) throws UnauthorizedException, UnknownUserException {
        Optional<User> optUser = USER_DAO.get(id, User.class);
        if (optUser.isEmpty()) {
            throw new UnknownUserException();
        } else {
            User user = optUser.get();
            // Control role of deletedUser and role of connected User
            if (user.isSuperAdmin() && !isSuperAdmin(connectedUserRole)) {
                throw new UnauthorizedException("You are not allowed to delete super-administrators");
            } else {
                return USER_DAO.delete(id, User.class);
            }
        }
    }

    public Role getRoleByName(String roleName) throws UnknownValueException {
        Optional<Role> optRole = new RoleDao().getByName(roleName);
        if(optRole.isPresent()) {
            return optRole.get();
        }
        throw new UnknownValueException("Role", roleName);
    }

    private boolean isValidPassword(String password, boolean byPass) {
        if(byPass) return true;
        int uppercaseCounter =0;
        int lowercaseCounter =0;
        int digitCounter =0;
//        int specialCounter =0;
        for (int i=0; i < password.length(); i++ ) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c))
                uppercaseCounter++;
            else if(Character.isLowerCase(c))
                lowercaseCounter++;
            else if(Character.isDigit(c))
                digitCounter++;
            /*if(c>=33&&c<=46||c==64){
                specialCounter++;
            }*/
        }
        return password.length() > 7 && uppercaseCounter > 0 && lowercaseCounter > 0 && digitCounter > 0;
    }
    private boolean isValidPseudo(String pseudo) {
        int minLength = 2;
        int maxLength = 20;
        return !( pseudo.length() < minLength || pseudo.length() > maxLength );
    }
    private boolean isValidEmail(String email) {
        int minLength = 6;
        return ( email.length() >= minLength && email.indexOf('@') >=0 );
    }

    private boolean isSuperAdmin(String roleName) {
        return Objects.equals(roleName, RoleEnum.ROLE_SUPER_ADMIN.name());
    }
}
