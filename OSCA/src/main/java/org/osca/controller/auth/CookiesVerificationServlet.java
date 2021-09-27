package org.osca.controller.auth;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "CookiesVarificationServlet", value = "/CookiesVarificationServlet")
public class CookiesVerificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token =request.getParameter("osca");
        int ut = 0;
        System.out.println(token);
        if(token != null){
            JWebToken jwtObj = null;

            try {
                jwtObj = new JWebToken(token);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            if (jwtObj.isValid()){
                ut = jwtObj.getUserType(token);
            }

            else{
                ut = -1;
            }
        }
        else{
            ut = -1;
        }


        Gson gson = new Gson();
        String tokenJSON =gson.toJson(ut);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(tokenJSON);


    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token =request.getParameter("osca");
        int ut = 0;
        System.out.println(token);
        System.out.println("token");
        System.out.println("tokenisdururururur");
        if(token != null){
            JWebToken jwtObj = null;

            try {
                jwtObj = new JWebToken(token);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            if (jwtObj.isValid()){
                ut = jwtObj.getUserType(token);
            }

            else{
                ut = -1;
            }
        }
        else{
            ut = -1;
        }


        Gson gson = new Gson();
        String tokenJSON =gson.toJson(ut);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(tokenJSON);
    }
}
