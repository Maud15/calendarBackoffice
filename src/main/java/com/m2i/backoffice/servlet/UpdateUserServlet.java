package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.City;
import com.m2i.backoffice.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = UpdateUserServlet.URL)
public class UpdateUserServlet extends HttpServlet {

    public static final String URL = "/user/update";
    private static final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        City city = null;
        boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
        boolean superAdmin = Boolean.parseBoolean(req.getParameter("superAdmin"));

        boolean isSuccess = service.update(id, pseudo, email, firstname, lastname,city, admin,superAdmin);

        if (!isSuccess) {
            req.setAttribute("error", "Echec de la modification de l'utilisateur");
        }
        req.setAttribute("userId", id);
        req.setAttribute("info", "Utilisateur modifié avec succès");

        resp.sendRedirect(DetailsUserServlet.URL + "?userId=" + id);
    }

}
