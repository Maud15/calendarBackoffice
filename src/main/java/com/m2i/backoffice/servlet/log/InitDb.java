package com.m2i.backoffice.servlet.log;

import com.m2i.backoffice.dao.RoleDao;
import com.m2i.backoffice.model.Role;
import com.m2i.backoffice.model.RoleEnum;
import com.m2i.backoffice.service.UserService;
import com.m2i.backoffice.service.exception.UserCreationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = InitDb.URL)
public class InitDb extends HttpServlet {

    public static final String URL = "/init";

    //TODO : improve (surtout le bouton sur la page de login !)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RoleDao roleDao = new RoleDao();
        roleDao.create(new Role(RoleEnum.ROLE_USER));
        roleDao.create(new Role(RoleEnum.ROLE_ADMIN));
        roleDao.create(new Role(RoleEnum.ROLE_SUPER_ADMIN));
        try {
            new UserService().create("sa", "sa@gmail.com", "mdp", "admin","super",null, RoleEnum.ROLE_SUPER_ADMIN.name());
            new UserService().create("toto", "toto@gmail.com", "mdp", "toto","admin",null, RoleEnum.ROLE_ADMIN.name());
            new UserService().create("tutu", "tutu@gmail.com", "mdp", "tutu","user",null, RoleEnum.ROLE_USER.name());
        } catch (UserCreationException e) {
            e.printStackTrace();
            System.out.println(e.getServerMessage());
            req.setAttribute("error", e.getUiMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
}
