package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ShowOrganizer;
import org.osca.model.SuperAdminDashboard;
import org.osca.service.SARemoveSOService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SARemoveSOServlet", value = "/SARemoveSOServlet")
public class SARemoveSOServlet extends HttpServlet {
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

        String fname = tokennObj.getFirstName(token);
        int userType = tokennObj.getUserType(token);

        SARemoveSOService saRemoveSOserivice=new SARemoveSOService();
        ArrayList<ShowOrganizer> details = new ArrayList<>();
        try {
            details = saRemoveSOserivice.getShowOrganizersToRemove();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ShowOrganizer so = new ShowOrganizer(1, fname);
        details.add(0,so);

        Gson gson = new Gson();
        System.out.println(so);
        String saobj =gson.toJson(so);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
