package com.m2i.backoffice.servlet;

import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ListUserServlet.URL)
public class ListUserServlet extends HttpServlet {

    public static final String URL = "/users";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        System.out.println("Tous les utilisateurs");
        List<User> usersList = userDao.getAll();
        for (User user: usersList ) {
            System.out.println(user.getPseudo());
        }
        req.setAttribute("usersList", usersList);
        req.getRequestDispatcher("/WEB-INF/user/list-user.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

