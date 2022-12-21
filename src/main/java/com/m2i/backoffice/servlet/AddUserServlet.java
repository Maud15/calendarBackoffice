package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.City;
import com.m2i.backoffice.model.User;
import com.m2i.backoffice.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = AddUserServlet.URL)
public class AddUserServlet extends HttpServlet {

    public static final String URL = "/users/add";
    private static final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String cityStr = req.getParameter("city");
        //todo: manage city
        City city = null;
        boolean admin = Boolean.getBoolean(req.getParameter("admin"));
        boolean superAdmin = Boolean.getBoolean(req.getParameter("superAdmin"));

        User newUser = service.create(pseudo, email, password, firstname, lastname, city, admin, superAdmin);

        if(newUser != null) {
            req.setAttribute("info","Utilisateur "+ newUser.getPseudo() +" créé avec succès");
            req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
        } else {
            req.setAttribute("error", "Impossible de créer l'utilisateur");
            req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req, resp);
        }

    }
}
