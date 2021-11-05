package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.License;
import org.osca.service.ApplyLicenseService;
import org.osca.service.ImageService;
import org.osca.service.SAdashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LicensePaymentServlet", value = "/LicensePaymentServlet")
public class LicensePaymentServlet extends HttpServlet {
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
            path = dp.getUserDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";
        try {
            fname = fnameS.getShowOrganizerName(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        License a = new License();
        a.setFname(fname);
        a.setDPpath(path);
        a.setUtype(utype);

        System.out.println(a);
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
        int ut = tokennObj.getUserType(token);

        Gson gson = new Gson();
        License detail = gson.fromJson(body, License.class);

        ApplyLicenseService service = new ApplyLicenseService();

        double x = 0;

        try {
            x = service.getTotalFee(detail.getConcertID());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        License a = new License();
        a.setTotalFee(x);

        System.out.println(a);
        gson = new Gson();
        String saobj =gson.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

    }
}
