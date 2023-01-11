package com.m2i.backoffice.servlet;

import com.m2i.backoffice.service.UserService;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String pseudo = req.getParameter("pseudo");
        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String roleName = req.getParameter("role");
        String cityName = null;

        boolean isSuccess = service.update(req.getSession(false).getAttribute("role").toString(), id, pseudo, email, firstname, lastname, cityName , roleName);

        if (!isSuccess) {
            req.setAttribute("error", "Echec de la modification de l'utilisateur");
        }
        req.setAttribute("userId", id);
        req.setAttribute("info", "Utilisateur modifié avec succès");

        resp.sendRedirect(DetailsUserServlet.URL + "?userId=" + id);
    }

}
