package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.RoleEnum;
import com.m2i.backoffice.model.User;
import com.m2i.backoffice.service.UserService;

import com.m2i.backoffice.service.exception.UnknownValueException;
import com.m2i.backoffice.service.exception.UserCreationException;
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
    private static final UserService USER_SERV = new UserService();

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
        try {
            Optional<User> optNewUser = USER_SERV.create(
                    req.getParameter("pseudo"),
                    req.getParameter("email").toLowerCase(),
                    req.getParameter("password"),
                    req.getParameter("firstname"),
                    req.getParameter("lastname"),
                    req.getParameter("city"),
                    req.getParameter("role")
            );
            if(optNewUser.isPresent()) {
                User newUser = optNewUser.get();
                req.setAttribute("info","Utilisateur "+ newUser.getPseudo() +" créé avec succès");
            } else {
                System.out.println("Error while creating user");
                req.setAttribute("error", "Impossible de créer l'utilisateur");
            }
        }
        catch(UserCreationException e) {
            e.printStackTrace();
            System.out.println(e.getServerMessage());
            req.setAttribute("error", e.getUiMessage());
        }
        catch (UnknownValueException e) {
            e.printStackTrace();
            req.setAttribute("error", "Impossible de créer l'utilisateur : La valeur " + e.getData() + " est incorrecte pour le champ " + e.getDataName());
        } catch(Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Impossible de créer l'utilisateur");
        }
        req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
    }
}
