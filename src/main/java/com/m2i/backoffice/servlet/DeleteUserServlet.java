package com.m2i.backoffice.servlet;

import com.m2i.backoffice.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/users/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idUser = req.getParameter("idUserDelete");
        try{
            Long id = Long.parseLong(idUser);
            UserService service = new UserService();
            if(service.delete(id)){
                System.out.println("Delete happen");
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
