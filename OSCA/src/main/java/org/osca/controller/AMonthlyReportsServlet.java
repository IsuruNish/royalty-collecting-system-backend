package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.AMonthlyReport;
import org.osca.model.AMonthlyReportsYearAndMonth;
import org.osca.service.AMonthlyReportService;
import org.osca.service.AdashboardService;
import org.osca.service.ImageService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AMonthlyReportsServlet", value = "/AMonthlyReportsServlet")
public class AMonthlyReportsServlet extends HttpServlet {
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


        AMonthlyReportService monthlyReportService = new AMonthlyReportService();
        ArrayList<ArrayList<String>> DataArr = new ArrayList<>();
        try {
            DataArr = monthlyReportService.getReportTableDetails();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        AdashboardService Aservice = new AdashboardService();
        String fName=null;
        try {
            fName = Aservice.getDashboardDetails(uid).get(0);
            System.out.println(fName);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getEmpDP(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        AMonthlyReport aMonthlyReport = new AMonthlyReport();

        aMonthlyReport.setTableDataArr(DataArr);

        aMonthlyReport.setDpPath(path);
        aMonthlyReport.setuType(userType);
        aMonthlyReport.setfName(fName);

        Gson gson = new Gson();
        System.out.println(aMonthlyReport);
        String saobj =gson.toJson(aMonthlyReport);
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

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);
//        int uid = 1001;
//        &&!url.equals("/OSCA_war_exploded/AMonthlyReportsServlet")

        AMonthlyReportService monthlyReportService = new AMonthlyReportService();
        AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth;


        Gson gson = new Gson();
        aMonthlyReportsYearAndMonth = gson.fromJson(body,AMonthlyReportsYearAndMonth.class);
        String aobj =gson.toJson(aMonthlyReportsYearAndMonth);
        System.out.println(aMonthlyReportsYearAndMonth);
        int reportType = aMonthlyReportsYearAndMonth.getType();
        AMonthlyReport aMonthlyReport = new AMonthlyReport();
        if(reportType==1){
            ArrayList<ArrayList<String>>  incomingDetails = new ArrayList<>();
            try {
                incomingDetails=monthlyReportService.getIncomingDetails(aMonthlyReportsYearAndMonth);

                aMonthlyReport.setIncomingDetails(incomingDetails);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if(reportType==2){
            ArrayList<ArrayList<String>>  outgoingDetails = new ArrayList<>();
            try {
                outgoingDetails=monthlyReportService.getOutgoingDetails(aMonthlyReportsYearAndMonth);

                aMonthlyReport.setOutgoingDetails((outgoingDetails));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        Gson gson2 = new Gson();
        System.out.println(aMonthlyReport);
        String saobj =gson2.toJson(aMonthlyReport);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);







    }
}
