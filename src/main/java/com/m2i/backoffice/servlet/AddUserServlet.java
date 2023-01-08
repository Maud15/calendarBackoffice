package com.m2i.backoffice.servlet;

import com.m2i.backoffice.model.RoleEnum;
import com.m2i.backoffice.model.User;
import com.m2i.backoffice.service.UserService;

import com.m2i.backoffice.service.exception.InvalidPasswordException;
import com.m2i.backoffice.service.exception.UnknownValueException;
import com.m2i.backoffice.service.exception.UserAlreadyExistsException;
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
        try {
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
        } catch (UserAlreadyExistsException e) {
            req.setAttribute("error", "Impossible de créer l'utilisateur : " + e.getDataName() + " : " + e.getData() + "existe déjà");
        } catch (UnknownValueException e) {
            req.setAttribute("error", "Impossible de créer l'utilisateur : La valeur " + e.getData() + " est incorrecte pour le champ " + e.getDataName());
        } catch(InvalidPasswordException e) {
            req.setAttribute("error", "Le mot de passe doit contenir au moins 8 caratères, dont 1 minuscule, 1 majuscule et 1 chiffre");
        }
    req.getRequestDispatcher("/WEB-INF/user/add-user.jsp").forward(req,resp);
    }
}
