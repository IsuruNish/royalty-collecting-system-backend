package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ShowOrganizer;
import org.osca.model.UserLoginModel;
import org.osca.service.signupService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HeaderAndBody data = new HeaderAndBody();

        String header = data.getHeader(request);
        String body = data.getBody(request);
//        System.out.println(body);

        ShowOrganizer basicUser;
        Gson gson = new Gson();
        basicUser = gson.fromJson(body, ShowOrganizer.class);
        basicUser.setUserType(5);
        signupService service = new signupService();

        boolean added = false;

        try {
            added = service.addShowOrganizers(basicUser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String token = new JWebToken(basicUser.getFname(), basicUser.getLname(), basicUser.getEmail(), basicUser.getUserType()).toString();
        System.out.println(basicUser.getUserType());

        ShowOrganizer RealUser = new ShowOrganizer(basicUser.getUserType(), token);
        Gson g = new Gson();
        String res =g.toJson(RealUser);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(res);

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
