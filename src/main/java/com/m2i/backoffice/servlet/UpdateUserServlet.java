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

@WebServlet(urlPatterns = UpdateUserServlet.URL)
public class UpdateUserServlet extends HttpServlet {

    public static final String URL = "/user/update";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
        System.out.println("SI MODIFICATION SUR CHAMP SUPERADMIN VEFIFIER QUE L'ADMIN CONNECTE EST BIEN UN SUPERADMIN");
        Long id = Long.parseLong(req.getParameter("id"));
        String pseudo = req.getParameter("pseudo");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        City city = null;
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String adm = req.getParameter("admin");
        boolean admin;
        if(adm == null){
            admin = false;
        }
        else{
            admin = true;
        }

//        if(currentUser.isSuperAdmin()) {
//            String superAdmin = req.getParameter("superAdmin");
//            User user = new User(email,pseudo,password,admin,superAdmin,firstname,lastname,city);
//        } else {
            User user = new User(id, email, pseudo, password, admin, firstname, lastname, city);

//        }

        try {
            boolean isSuccess = new UserDao().update(user);
            /*if(!isSuccess){
                req.setAttribute("error","Echec de la modification de l'utilisateur");
            }
            req.setAttribute("userId",id);
            req.setAttribute("info","Utilisateur modifié avec succès");*/

            resp.sendRedirect(DetailsUserServlet.URL + "?userId=" + id);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
