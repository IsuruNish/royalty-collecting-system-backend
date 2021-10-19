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
import java.util.ArrayList;

@WebServlet(name = "ForgotPasswordServlet", value = "/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
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

        boolean isRecord = false;
        boolean ismoreThan2mins = true;
        boolean otpSent = false;
        ArrayList<Integer> uIDofUser = null;

        try {
            System.out.println(forgetpw);
            uIDofUser = service.checkEmail(forgetpw);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if (uIDofUser.get(0) != 0) {
            try {

                isRecord = service.checkIfTheresArecord(forgetpw);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            if(isRecord){
                try {

                    ismoreThan2mins = service.checkIf2minsHavePassed(forgetpw, uIDofUser.get(0));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (ismoreThan2mins){
                try {
                    System.out.println("4");

                    otpSent = service.sendOTP(forgetpw, uIDofUser.get(0), uIDofUser.get(1));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }



        }

        System.out.println("000");


        Gson gsonRes = new Gson();
        String saobj;

        if (uIDofUser.get(0) == 0){
            saobj =gsonRes.toJson(new Respond(0));
        }

        else if(!ismoreThan2mins){
            saobj =gsonRes.toJson(new Respond(1));
        }

        else if(!otpSent){
            saobj =gsonRes.toJson(new Respond(2));
        }

        else{
            saobj =gsonRes.toJson(new Respond(3));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }
}
