package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.UpcomingPayment;
import org.osca.service.ImageService;
import org.osca.service.SAdashboardService;
import org.osca.service.SAupcomingPaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SAoscaPaymentMoreDetailsServlet", value = "/SAoscaPaymentMoreDetailsServlet")
public class SAoscaPaymentMoreDetailsServlet extends HttpServlet {
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

        UpcomingPayment sa = new UpcomingPayment();
        sa.setUt(utype);
        sa.setDPpath(path);
        sa.setFname(fname);

        Gson gson = new Gson();
        String saobj =gson.toJson(sa);
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
        UpcomingPayment detail = gson.fromJson(body, UpcomingPayment.class);

        SAupcomingPaymentService service = new SAupcomingPaymentService();


        ArrayList<String> details = new ArrayList<>();

        System.out.println(detail.getTypeOfreq());
        System.out.println("heyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        if (detail.getTypeOfreq() == 1){
            try {
                details = service.getShowOrganizerDetails(detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            try {
                details = service.getMemberDetails(detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }


        UpcomingPayment sa = new UpcomingPayment();
        sa.setData(details);
        sa.setUt(ut);

        Gson gson2 = new Gson();
        String saobj =gson2.toJson(sa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }
}
