package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.dao.ForgetPasswordDAOImpl;
import org.osca.model.ForgetPassword;
import org.osca.model.ResetPassword;
import org.osca.service.ForgetPasswordService;
import org.osca.service.ResetPasswordService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResetPasswordServlet", value = "/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ok");
        HeaderAndBody data = new HeaderAndBody();

        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);





//        System.out.println(body);

        ResetPassword resetpw;
        Gson gson = new Gson();
        resetpw = gson.fromJson(body, ResetPassword.class);
//        resetpw.setPassword("1234");
//        forgetpw.setUserType(5);
//        System.out.println("ok ok");
//        System.out.println(resetpw);
        ResetPasswordService service =new ResetPasswordService();

        boolean added = false;

        try {
            added = service.addPin(resetpw);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
