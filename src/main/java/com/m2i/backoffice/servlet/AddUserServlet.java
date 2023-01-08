package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.RoleEnum;
import com.m2i.backoffice.model.User;
import com.m2i.backoffice.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = AddUserServlet.URL)
public class AddUserServlet extends HttpServlet {

    public static final String URL = "/users/add";
    private static final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session.getAttribute("role") == RoleEnum.ROLE_SUPER_ADMIN.name()) {
            req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<User> optNewUser = service.create(
                req.getParameter("pseudo"),
                req.getParameter("email").toLowerCase(),
                req.getParameter("password"),
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("city"),
                req.getParameter("role")
        );

        if(optNewUser.isPresent()) {
            req.setAttribute("info","Utilisateur "+ optNewUser.get().getPseudo() +" créé avec succès");
        } else {
            req.setAttribute("error", "Impossible de créer l'utilisateur");
        }
        req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);

    }
}
