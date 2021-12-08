package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.*;
import org.osca.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AddUsersServlet", value = "/AddUsersServlet")
public class AddUsersServlet extends HttpServlet {
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
        int uid = tokennObj.getUserID(token);

        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getEmpDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";
        try {
            fname = fnameS.getSuperadminName(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AdminDashboard a = new AdminDashboard();
        a.setUtype(utype);
        a.setDPpath(path);
        a.setFname(fname);

        Gson gson = new Gson();
        String saobj =gson.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);
        String token = header.substring(7);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int uid = tokennObj.getUserID(token);

        AAddUsers user;
        Gson gson = new Gson();
        user = gson.fromJson(body, AAddUsers.class);
        AddUsersService service = new AddUsersService();

        boolean added = false;
        boolean checkMail = false;
        try {
            checkMail = service.validateEmail(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (checkMail && !user.getForWhom().equals("member")) {

            try {
                added = service.addOfficials(user, uid, user.getForWhom());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if (checkMail && user.getForWhom().equals("member")){
            int isNonMember = 0;
            MemberDashboard Muser = gson.fromJson(body, MemberDashboard.class);

            try {
                isNonMember = service.checkMemberStatus2(Muser);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            System.out.println(isNonMember);
            if (isNonMember != 0){
                try {
                    added = service.changeNonmemberStatus2(Muser, isNonMember, uid);
                    //here

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
            else{
                try {
                    added = service.addMembers(Muser,uid);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        Gson g = new Gson();
        String res;
        if (!checkMail){
            res = g.toJson(new Respond(-1));
        }

        else if (added){
           res = g.toJson(new Respond(1));
        }

        else{
            res = g.toJson(new Respond(0));
        }
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
