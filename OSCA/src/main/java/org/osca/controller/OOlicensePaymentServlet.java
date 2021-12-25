package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.dao.OOlicensePaymentDAO;
import org.osca.model.Requests;
import org.osca.model.Respond;
import org.osca.service.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "OOlicensePaymentServlet", value = "/OOlicensePaymentServlet")
public class OOlicensePaymentServlet extends HttpServlet {
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
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";
        try {
            fname = fnameS.getSuperadminName(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        OOlicensePaymentService service = new OOlicensePaymentService();
        ArrayList<ArrayList<String>> license1 = new ArrayList<>();
        ArrayList<String> info = new ArrayList<>();

        try {
            license1 = service.getLicensePaymentData();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        info.add(fname);
        info.add(String.valueOf(utype));
        info.add(String.valueOf(uid));
        info.add(path);

        Requests req = new Requests();
        req.setLicenseAppReqs(license1);
        req.setInfo(info);

        Gson g = new Gson();
        String sa;

        sa = g.toJson(req);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);
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
        int ut = tokennObj.getUserType(token);

        Gson gson = new Gson();
        Requests detail = gson.fromJson(body, Requests.class);

        System.out.println(detail);

        OOlicensePaymentService service = new OOlicensePaymentService();
        NotificationService nService = new NotificationService();

        boolean done = false;

        if (detail.getReqType() == 1){
            try {
                done = service.licensePaymentAccepted(detail.getLicenseID());
            } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                throwables.printStackTrace();
            }
        }


        else if (detail.getReqType() == 2){
            try {
                done = service.licensePaymentDenied(detail.getLicenseID());
            } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                throwables.printStackTrace();
            }
        }


        Respond theRes = new Respond();

        if (done){
            theRes.setOk(1);
        }
        else{
            theRes.setOk(0);
        }

        Gson g = new Gson();
        String sa;

        sa = g.toJson(theRes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);
    }
}
