package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ACheckStat;
import org.osca.model.SOCheckStat;
import org.osca.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ACheckStatServlet", value = "/ACheckStatServlet")
public class ACheckStatServlet extends HttpServlet {
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

   // int uid=10001;
        ACheckStatService checkStatService=new ACheckStatService();
        ArrayList<ArrayList<Double>>  monthDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  yearDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  MemberIncomeMonthDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  MemberIncomeYearDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  LicenseIncomeYearDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  LicenseIncomeMonthDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  DistributionReportMonthDetails = new ArrayList<>();
        ArrayList<ArrayList<Double>>  DistributionReportYearDetails = new ArrayList<>();
        try {
            monthDetails=checkStatService.getPaymentHistory(uid);
            yearDetails=checkStatService.getYearPaymentHistory(uid);
            MemberIncomeMonthDetails=checkStatService.getMemberIncomeMonthDetails(uid);
            MemberIncomeYearDetails=checkStatService.getMemberIncomeYearDetails(uid);
            LicenseIncomeYearDetails=checkStatService.getLicenseIncomeYearDetails(uid);
            LicenseIncomeMonthDetails=checkStatService.getLicenseIncomeMonthDetails(uid);
            DistributionReportMonthDetails=checkStatService.getDistributionReportMonthDetails(uid);
            DistributionReportYearDetails=checkStatService.getYDistributionReportYearDetails(uid);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        AdashboardService Aservice = new AdashboardService();
        String fName=null;
        try {
            fName = Aservice.getDashboardDetails(uid).get(0);
            System.out.println(fName);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getEmpDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ACheckStat checkStat=new ACheckStat();
        checkStat.setMonthDetails(monthDetails);
        checkStat.setYearDetails(yearDetails);
        checkStat.setMemberIncomeMonthDetails(MemberIncomeMonthDetails);
        checkStat.setMemberIncomeYearDetails(MemberIncomeYearDetails);
        checkStat.setLicenseIncomeYearDetails(LicenseIncomeYearDetails);
        checkStat.setLicenseIncomeMonthDetails(LicenseIncomeMonthDetails);
        checkStat.setDistributionReportMonthDetails(DistributionReportMonthDetails);
        checkStat.setDistributionReportYearDetails(DistributionReportYearDetails);
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
