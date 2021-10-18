package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.AAddUsers;
import org.osca.model.AdminDashboard;
import org.osca.model.Respond;
import org.osca.model.ShowOrganizer;
import org.osca.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AAddOfficialsServlet", value = "/AAddOfficialsServlet")
public class AAddOfficialsServlet extends HttpServlet {
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

        int utype = tokennObj.getUserType(token);

        AdminDashboard a = new AdminDashboard();
        a.setUtype(utype);
        Gson gson = new Gson();
        String saobj =gson.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HeaderAndBody data = new HeaderAndBody();
        System.out.println("ok ok");
        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);
        String token = header.substring(7);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        String email = tokennObj.getEmail(token);
        int uid = tokennObj.getUserID(token);


//        System.out.println(body);

        AAddUsers user=new AAddUsers();
        Gson gson = new Gson();
        user = gson.fromJson(body, AAddUsers.class);
        user.setUserType(3);
        AAddUsersService service = new AAddUsersService();

        boolean added = false;
         int isuru=0;
        try {
            System.out.println(user);
            added = service.addOscaOfficials(user,uid);
            if(added)isuru=1;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        String token2 = new JWebToken(user.getFname(), user.getLname(), user.getEmail(), user.getUserType()).toString();
//        System.out.println(user.getUserType());
//
//        AAddUsers RealUser = new AAddUsers(user.getUserType(), token2);

//        Gson g = new Gson();
//        String res =g.toJson(new Respond(isuru));
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        response.getWriter().println(res);

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
