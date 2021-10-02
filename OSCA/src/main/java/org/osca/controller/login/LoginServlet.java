package org.osca.controller.login;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.model.UserLoginModel;
import org.osca.service.LoginService;
import org.osca.controller.httpRequest.HeaderAndBody;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HeaderAndBody data = new HeaderAndBody();

        String body = data.getBody(request);

        UserLoginModel user = new UserLoginModel();

        Gson gson = new Gson();
        user = gson.fromJson(body, UserLoginModel.class);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String email= user.getEmail();
        String password= user.getPassword();

        LoginService service = new LoginService();
        UserLoginModel RealUser = new UserLoginModel();

        try {
            RealUser = service.getUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(RealUser==null){
            Gson g = new Gson();
            String ut =g.toJson(new UserLoginModel(0));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(ut);
        }

        else{
            String token = new JWebToken(RealUser.getFirstName(), RealUser.getLastName(), RealUser.getEmail(), RealUser.getUserType()).toString();
            RealUser.setToken(token);
            Gson g = new Gson();
            String ut =g.toJson(new UserLoginModel(RealUser.getUserType(), RealUser.getToken()));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().println(ut);
        }
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }


}
