package com.m2i.backoffice.servlet;


import com.m2i.backoffice.dao.UserDao;
import com.m2i.backoffice.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/users/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idUser = req.getParameter("idUserDelete");
        try{
            Long id = Long.parseLong(idUser);
            UserDao userDao = new UserDao();
            Optional<User> user = userDao.get(id);
            if(user.isPresent()){
                userDao.delete(id);
            }
            else{
                System.err.println("User not found ! Can't delete it !");
            }
        } catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }

}
