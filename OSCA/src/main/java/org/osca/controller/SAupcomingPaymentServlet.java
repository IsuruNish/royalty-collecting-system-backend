package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.License;
import org.osca.model.Respond;
import org.osca.model.UpcomingEvents;
import org.osca.model.UpcomingPayment;
import org.osca.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SAupcomingPaymentServlet", value = "/SAupcomingPaymentServlet")
public class SAupcomingPaymentServlet extends HttpServlet {
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

        SAupcomingPaymentService UPservice = new SAupcomingPaymentService();
        SAdashboardService saService = new SAdashboardService();
        ArrayList<ArrayList<String>> details = new ArrayList<>();
        ArrayList<ArrayList<String>> details2 = new ArrayList<>();

        try {
            details = UPservice.getCancelledLicense();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (ArrayList<String> detail : details) {
            int userID = Integer.parseInt(detail.get(0));

            try {
                detail.add(saService.getShowOrganizerFULLName(userID));
                detail.add(dp.getUserDP(userID));
                detail.add("1");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            details2 = UPservice.getMemberPayments();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (ArrayList<String> detail : details2) {
            int userID = Integer.parseInt(detail.get(2));

            try {
                detail.add(dp.getMemDP(userID));
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        UpcomingPayment sa = new UpcomingPayment();
        sa.setUt(utype);
        sa.setDPpath(path);
        sa.setFname(fname);
        sa.setLicense(details);
        sa.setMusic(details2);

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

        boolean done = false;

        if (detail.getTypeOfreq() == 100){
            try {
                done = service.setPaymentShowOrganizer(detail.getUserID(), detail.getConcertID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else{
            try {
                System.out.println(detail.getUserID());
                System.out.println(detail.getConcertID());
                System.out.println(detail.getSongID());
                done = service.setPaymentMember(detail.getMemberID(), detail.getConcertID(),detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        Respond sa = new Respond();

        if (done){
            sa.setOk(1);
        }
        else{
            sa.setOk(0);
        }

        Gson gson2 = new Gson();
        String saobj =gson2.toJson(sa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }
}
