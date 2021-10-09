package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.controller.login.Mail;
import org.osca.model.AdminDashboard;

import org.osca.service.AdashboardService;


import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdashboardServlet", value = "/AdashboardServlet")
public class AdashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String token = header.substring(7);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        String fname = tokennObj.getFirstName(token);
//        String lname = tokennObj.getLastName(token);
//        String email = tokennObj.getEmail(token);
//        int userType = tokennObj.getUserType(token);

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);


        AdashboardService SAserivice=new AdashboardService();
        ArrayList<String> details = new ArrayList<>();
        try {
            System.out.println("ok");
            details = SAserivice.getDashboardDetails(uid);
            System.out.println("nice");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AdminDashboard sa = new AdminDashboard(
                2,
                uid,
                details.get(0),
                details.get(1),
                details.get(2),
                details.get(3),
                Integer.parseInt(details.get(2)),
                Integer.parseInt(details.get(3)),
                Integer.parseInt(details.get(4)),
                Integer.parseInt(details.get(5)),
                Double.parseDouble(details.get(6)),
                Integer.parseInt(details.get(7)),
                Double.parseDouble(details.get(8)));

        Gson gson = new Gson();
        System.out.println(sa);
        String saobj =gson.toJson(sa);
        System.out.println(saobj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

//        Mail javaMailUtil=new Mail();
//        try {
//            javaMailUtil.sendMail("osca.g04@gmail.com",""+"1234");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
