package com.atacadao.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = resq.getSession(false);
        if(session != null){
            session.invalidate();
        }
        resp.sendRedirect(resq.getContextPath() + "/pages/login.jsp");
    }
}
