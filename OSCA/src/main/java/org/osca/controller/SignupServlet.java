package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.model.ShowOrganizer;
import org.osca.service.signupService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class SignupServlet extends HttpServlet {
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

        String hashedPW = doHash(pw);


        signupService service = new signupService();
        
        boolean added = false;

        ShowOrganizer buser = new ShowOrganizer(fname,lname,phone,nic, email, hashedPW);
        try {
            added = service.addShowOrganizers(buser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String token = new JWebToken(buser.getFname(), buser.getLname(), buser.getEmail(), buser.getUserType()).toString();

        Gson gson = new Gson();
        String tokenJSON =gson.toJson(token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(tokenJSON);

    }

    public String doHash(String password){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] hashedByte=messageDigest.digest();
            StringBuilder sb=new StringBuilder();
            for(byte b:hashedByte){
                sb.append(String.format("%02x",b));
            }
            return  sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
