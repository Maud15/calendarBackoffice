package com.m2i.backoffice.servlet.log;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.Role;
import com.m2i.backoffice.model.User;
import com.m2i.backoffice.servlet.ListUserServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = Login.URL)
public class Login extends HttpServlet {

    public static final String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(ListUserServlet.URL);
        } else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Optional<User> optUser = new UserDao().getByPseudo(username);
            if(optUser.isPresent()) {
                User user = optUser.get();
                if (user.isAdminOrSuperAdmin() && BCrypt.checkpw(password, user.getPassword())) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("username", username);
                    Optional<Role> optUserRole = user.getRoleList().stream().findFirst();
                    optUserRole.ifPresent(role -> session.setAttribute("role", role.getName().name()));
                    session.setMaxInactiveInterval(30 * 60);

                    Cookie cookieUser = new Cookie("username", username);
                    resp.addCookie(cookieUser);

                    resp.sendRedirect(ListUserServlet.URL);
                } else {
                    req = setReqAttributesForLoginFail(req);
                    doGet(req, resp);
                }

            } else {
                req = setReqAttributesForLoginFail(req);
                doGet(req, resp);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public HttpServletRequest setReqAttributesForLoginFail(HttpServletRequest req) {
        req.setAttribute("loginFail", true);
        req.setAttribute("error", "Identifiant ou mot de passe erroné");
        return req;
    }

}
