package com.m2i.backoffice.filter;

import com.m2i.backoffice.servlet.log.Login;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*", "/user/*", "/logout"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if(session != null && session.getAttribute("username") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            resp.sendRedirect(Login.URL);
        }

    }

}
