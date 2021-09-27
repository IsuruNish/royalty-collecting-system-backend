package org.osca.controller.login;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.dao.*;
import org.osca.dao.LoginDAOImpl;
import org.osca.dao.LoginDAO;
import org.osca.model.UserLoginModel;
import org.osca.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String email=request.getParameter("email");
        String password=request.getParameter("password");
         //1256
        //password hashed - SHA256
        String hashedPW=doHash(password);
        UserLoginModel userLoginModel=new UserLoginModel();
        userLoginModel.setEmail(email);
        userLoginModel.setPassword(hashedPW);

        boolean checked = false;
        LoginService service = new LoginService();

        try {
            userLoginModel = service.getUser(userLoginModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if(userLoginModel==null){
            out.println("Credentials are not matched !");
        }else {

            //token
            JWebToken token = new JWebToken(userLoginModel.getFirstName(), userLoginModel.getLastName(), userLoginModel.getEmail(), userLoginModel.getUserType());


            //Session
            int userType=userLoginModel.getUserType();

            switch (userType) {
                case 1:     // Super Admin
                    out.println("1");
                    break;

                case 2:     // Admin
                    out.println("2");
                    break;

                case 3:     // OSCA official

                    out.println("3");
                    break;

                case 4:     // member
                    out.println("4");
                    break;

                case 5:    //show organizer
                    out.println("5");
                    break;

                default:
                    out.println("You can't log now..");
            }

            Gson gson = new Gson();
            String tokenJSON =gson.toJson(token);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().println(tokenJSON);

        }
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

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
