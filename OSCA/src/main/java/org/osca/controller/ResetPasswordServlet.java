package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ForgetPassword;
import org.osca.model.Respond;
import org.osca.service.ForgotPasswordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResetPasswordServlet", value = "/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HeaderAndBody data = new HeaderAndBody();
        String body = data.getBody(request);

        ForgetPassword forgetpw;
        Gson gson = new Gson();
        forgetpw = gson.fromJson(body, ForgetPassword.class);
        ForgotPasswordService service =new ForgotPasswordService();

        boolean changed = false;
        int verify = 0;

        try {
            verify = service.checkVerification(forgetpw);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (verify != 1){
            try {
                changed = service.changePassword(forgetpw);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        Gson gsonRes = new Gson();
        String saobj;

        if (changed){
            saobj =gsonRes.toJson(new Respond(1));
        }

        else{
            saobj =gsonRes.toJson(new Respond(0));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

    }
}
