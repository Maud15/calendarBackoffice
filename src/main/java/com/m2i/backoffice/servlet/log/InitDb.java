package com.m2i.backoffice.servlet.log;

import com.m2i.backoffice.dao.RoleDao;
import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.Role;
import com.m2i.backoffice.model.RoleEnum;
import com.m2i.backoffice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = InitDb.URL)
public class InitDb extends HttpServlet {

    public static final String URL = "/init";

    //TODO : improve (surtout le bouton sur la page de login !)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleDao roleDao = new RoleDao();
        roleDao.create(new Role(RoleEnum.ROLE_USER));
        roleDao.create(new Role(RoleEnum.ROLE_ADMIN));
        Role roleSA = roleDao.create(new Role(RoleEnum.ROLE_SUPER_ADMIN));
        new UserDao().create(new User("sa", "sa@gmail.com", "mdp", "admin","super",null, List.of(roleSA)));
    }
}
