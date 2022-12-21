package com.m2i.backoffice.servlet.log;

import com.m2i.backoffice.servlet.ListUserServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = Login.URL)
public class Login extends HttpServlet {

    public static final String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("username") != null){
            resp.sendRedirect(ListUserServlet.URL);
        } else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(username.equals("Maud") && password.equals("azerty")) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(30 * 60);

            Cookie cookieUser = new Cookie("username", username);
            resp.addCookie(cookieUser);

            resp.sendRedirect(ListUserServlet.URL);
        } else {
            req.setAttribute("loginFail", true);
            req.setAttribute("error", "Couple identifiant / Mot de passe inconnu");
            doGet(req, resp);
        }
    }

}
