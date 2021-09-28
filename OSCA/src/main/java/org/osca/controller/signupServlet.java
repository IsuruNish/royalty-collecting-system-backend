package org.osca.controller;

import org.osca.model.ShowOrganizer;
import org.osca.service.signupService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


public class signupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String nic = req.getParameter("nic");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String pw = req.getParameter("pw");

        signupService service = new signupService();
        
        boolean added = false;

        try {
            added = service.addShowOrganizers(new ShowOrganizer(fname,lname,phone,nic, email, pw));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
