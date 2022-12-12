package com.m2i.backoffice.servlet;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.City;
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
        String email = req.getParameter("email").toLowerCase();
        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String cityStr = req.getParameter("city");
        //todo: manage city
        City city = null;
        boolean admin = Boolean.getBoolean(req.getParameter("admin"));
        boolean superAdmin = Boolean.getBoolean(req.getParameter("superAdmin"));

        User newUser = new User(email,pseudo,password,admin, superAdmin, firstname,lastname,city);
        new UserDao().create(newUser);

//        resp.sendRedirect(req.getContextPath() + ListUserServlet.URL);
        resp.sendRedirect(req.getContextPath() + AddUserServlet.URL);
    }
}
