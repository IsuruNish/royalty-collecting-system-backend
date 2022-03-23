package org.osca.controller.login;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.*;
import org.osca.service.AddUsersService;
import org.osca.service.ImageService;
import org.osca.service.LoginService;
import org.osca.service.SAdashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "TwoFactorAuthServlet", value = "/TwoFactorAuthServlet")
public class TwoFactorAuthServlet extends HttpServlet {
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

        int uid = tokennObj.getUserID(token);
        int ut = tokennObj.getUserType(token);

        SMSgateway sms = new SMSgateway();
        LoginService log = new LoginService();
        boolean done = false;

        int pin = 0;
        try {
            pin = sms.sendSMSPin();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            done = log.set2FactorAuth(uid, pin);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(pin);
        System.out.println(ut);
        System.out.println(done);

        if (done){
            Respond res= new Respond();
            res.setOk(1);
            res.setUserType(ut);
            Gson gson = new Gson();
            String saobj =gson.toJson(res);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }

        else{
            Respond res= new Respond();
            res.setOk(0);
            Gson gson = new Gson();
            String saobj =gson.toJson(res);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }
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
        Gson gson = new Gson();

        TwoFactorAuth authe = gson.fromJson(body, TwoFactorAuth.class);

        LoginService log = new LoginService();

        boolean done = false;
        if (authe.getReqType() == 1){
            try {
                done = log.get2FactorAuth(uid, authe.getPin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(done);

            if (done){
                Respond res= new Respond();
                res.setOk(1);
                Gson gson2 = new Gson();
                String saobj =gson2.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }

            else{
                Respond res= new Respond();
                res.setOk(0);
                Gson gson2 = new Gson();
                String saobj =gson2.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }
        }

        else if (authe.getReqType() == 2){
            SMSgateway sms = new SMSgateway();

            int pin = 0;
            try {
                pin = sms.sendSMSPin();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                done = log.set2FactorAuth(uid, pin);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            if (done){
                Respond res= new Respond();
                res.setOk(1);
                Gson gson2 = new Gson();
                String saobj =gson2.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }

            else{
                Respond res= new Respond();
                res.setOk(0);
                Gson gson2 = new Gson();
                String saobj =gson2.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }
        }

    }
}
