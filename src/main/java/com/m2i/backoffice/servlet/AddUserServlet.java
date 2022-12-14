package com.m2i.backoffice.servlet;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = AddUserServlet.URL)
public class AddUserServlet extends HttpServlet {

    public static final String URL = "/users/add";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String userPseudo = req.getParameter("userPseudo");
        String userPassword = req.getParameter("userPassword");
        boolean isAdmin = Boolean.getBoolean(req.getParameter("isAdmin"));

        User newUser = new User(userEmail,userPseudo,userPassword,isAdmin);
        System.out.println(userEmail + " " + userPseudo + " " + userPassword + "  " + isAdmin);
        new UserDao().create(newUser);

//        resp.sendRedirect(req.getContextPath() + ListUserServlet.URL);
        resp.sendRedirect(req.getContextPath() + AddUserServlet.URL);
    }
}
