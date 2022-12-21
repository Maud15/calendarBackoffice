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
    private static final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strUserId = req.getParameter("userId");
        Long userId;
        if(strUserId == null) {
            userId = (Long) req.getAttribute("userId");
        } else {
            userId = Long.parseLong(strUserId);
        }
        Optional<User> optUser = service.get(userId);
        if(optUser.isPresent()) {
            req.setAttribute("user", optUser.get());
            req.getRequestDispatcher("/WEB-INF/user/details-user.jsp").forward(req,resp);
        } else {
            req.setAttribute("error","Utilisateur introuvable");
            req.getRequestDispatcher(ListUserServlet.URL).forward(req,resp);
        }
    }

}
