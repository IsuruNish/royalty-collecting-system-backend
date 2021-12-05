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

        int isValidEmail = 0;
        int id = 0;
        try {
            RealUser = service.getUser(user);
            id = service.getID(user);
            System.out.println(id);

            RealUser.setId(id);

            if (id != 0){
                isValidEmail = service.isEmailVerified(user);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(RealUser==null){
            Gson g = new Gson();
            String ut =g.toJson(new UserLoginModel(0));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(ut);
        }

        else{
            String ut = null;
            if (isValidEmail == -1){
                String token = new JWebToken(RealUser.getId(), RealUser.getUserType()).toString();
                RealUser.setToken(token);
                Gson g = new Gson();
                ut =g.toJson(new UserLoginModel(RealUser.getUserType(), RealUser.getToken()));
            }
            else{
                String token = new JWebToken(RealUser.getId(), RealUser.getUserType()).toString();
                RealUser.setToken(token);
                Gson g = new Gson();
                ut =g.toJson(new UserLoginModel(isValidEmail, RealUser.getToken()));
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(ut);
        }
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }


}
