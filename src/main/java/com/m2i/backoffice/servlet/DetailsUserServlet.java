package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.User;
import com.m2i.backoffice.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = DetailsUserServlet.URL)
public class DetailsUserServlet  extends HttpServlet {

    public static final String URL = "/user/details";
    private static final UserService usrService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        Optional<User> optUser = usrService.get(userId);
        if(optUser.isPresent()) {
            req.setAttribute("user", optUser.get());
            req.getRequestDispatcher("/WEB-INF/user/details-user.jsp").forward(req,resp);
        } else {
            req.setAttribute("error","Impossible de trouver l'utilisateur d'id : " + userId);
            //req.getRequestDispatcher(ListUserServlet.URL).forward(req,resp);
            req.getRequestDispatcher(AddUserServlet.URL).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
        System.out.println("USER DETAILS SERVLET, METHOD POST NOT IMPLEMENTED");
    }

}
