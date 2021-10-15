package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.SuperAdminDashboard;
import org.osca.service.ImageService;
import org.osca.service.SAdashboardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SAdashboardServlet", value = "/SAdashboardServlet")
public class SAdashboardServlet extends HttpServlet {
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
//        String fname = tokennObj.getFirstName(token);
//        String lname = tokennObj.getLastName(token);
//        String email = tokennObj.getEmail(token);
        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

        SAdashboardService SAserivice=new SAdashboardService();
        ArrayList<String> details = new ArrayList<>();
        try {
            details = SAserivice.getDashboardDetails(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

        SuperAdminDashboard sa = new SuperAdminDashboard(
                Integer.parseInt(details.get(11)),
                uid,
                details.get(0),
                details.get(1),
                details.get(2),
                details.get(3),
                Integer.parseInt(details.get(4)),
                Integer.parseInt(details.get(5)),
                Integer.parseInt(details.get(6)),
                Integer.parseInt(details.get(7)),
                Double.parseDouble(details.get(8)),
                Integer.parseInt(details.get(9)),
                Double.parseDouble(details.get(10)),
                path);

            Gson gson = new Gson();
            System.out.println(sa);
            String saobj =gson.toJson(sa);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
