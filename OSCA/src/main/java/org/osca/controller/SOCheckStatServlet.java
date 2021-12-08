package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.SOCheckStat;
import org.osca.model.SuperAdminDashboard;
import org.osca.service.ImageService;
import org.osca.service.SAdashboardService;
import org.osca.service.SOCheckStatService;
import org.osca.service.ShowOrganizerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SOCheckStatServlet", value = "/SOCheckStatServlet")
public class SOCheckStatServlet extends HttpServlet {
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

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

//    int uid=10001;
        SOCheckStatService checkStatService=new SOCheckStatService();
        ArrayList<ArrayList<Double>>  monthDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  yearDetails = new ArrayList<>();
        try {
            monthDetails=checkStatService.getPaymentHistory(uid);
            yearDetails=checkStatService.getYearPaymentHistory(uid);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        ShowOrganizerService SOserivice=new ShowOrganizerService();
        String fName=null;
        try {
            fName = SOserivice.getDashboardDetails(uid).get(1);
            System.out.println(fName);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getUserDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        SOCheckStat checkStat=new SOCheckStat();
        checkStat.setMonthDetails(monthDetails);
        checkStat.setYearDetails(yearDetails);
        checkStat.setDpPath(path);
        checkStat.setuType(userType);
        checkStat.setfName(fName);

        Gson gson = new Gson();
        System.out.println(checkStat);
        String saobj =gson.toJson(checkStat);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
