package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ACheckReports;
import org.osca.model.AMonthlyReport;
import org.osca.model.AMonthlyReportsYearAndMonth;
import org.osca.model.Respond;
import org.osca.service.ACheckReportsService;
import org.osca.service.AMonthlyReportService;
import org.osca.service.AdashboardService;
import org.osca.service.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ACompletedOutgoingDetailsServlet", value = "/ACompletedOutgoingDetailsServlet")
public class ACompletedOutgoingDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HeaderAndBody data = new HeaderAndBody();
//        String header = data.getAuthenticationHeader(request);
//        String token = header.substring(7);
//
//        JWebToken tokennObj = null;
//        try {
//            tokennObj = new JWebToken(token);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        assert tokennObj != null;
//        int uid = tokennObj.getUserID(token);
//        int userType = tokennObj.getUserType(token);
//
//
//        AdashboardService Aservice = new AdashboardService();
//        String fName=null;
//        try {
//            fName = Aservice.getDashboardDetails(uid).get(0);
//            System.out.println(fName);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        ImageService dp = new ImageService();
//        String path = null;
//        try {
//            path = dp.getEmpDP(uid);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        AMonthlyReport aMonthlyReport = new AMonthlyReport();
//
//
//        aMonthlyReport.setDpPath(path);
//        aMonthlyReport.setuType(userType);
//        aMonthlyReport.setfName(fName);
//
//        Gson gson = new Gson();
//        System.out.println(aMonthlyReport);
//        String saobj =gson.toJson(aMonthlyReport);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(saobj);

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

        assert tokennObj != null;
        int userType = tokennObj.getUserType(token);
//        int uid = 1001;
//        &&!url.equals("/OSCA_war_exploded/AMonthlyReportsServlet")

//        AMonthlyReportService monthlyReportService = new AMonthlyReportService();
        AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth;

        Gson gson = new Gson();
        aMonthlyReportsYearAndMonth = gson.fromJson(body,AMonthlyReportsYearAndMonth.class);
        String aobj =gson.toJson(aMonthlyReportsYearAndMonth);
        System.out.println(aMonthlyReportsYearAndMonth);

        AMonthlyReport aMonthlyReport = new AMonthlyReport();
        int reportType = aMonthlyReportsYearAndMonth.getType();
        ACheckReportsService aCheckReportsService = new ACheckReportsService();

        boolean checked = true;
        try {
            checked = aCheckReportsService.getcheckDetails(aMonthlyReportsYearAndMonth);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Respond res = new Respond();
        if (checked){
            res.setOk(1);
        }
        else{
            res.setOk(0);
        }

        res.setUserType(userType);
        String saobj =gson.toJson(res);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }


}
