package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ShowOrganizer;
import org.osca.service.ImageService;
import org.osca.service.SARemoveSOService;
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

@WebServlet(name = "SARemoveMemServlet", value = "/SARemoveMemServlet")
public class SARemoveMemServlet extends HttpServlet {
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

        int uid = tokennObj.getUserID(token);

        SARemoveSOService saRemoveSOserivice=new SARemoveSOService();
        SAdashboardService saSerivice=new SAdashboardService();
        ArrayList<ShowOrganizer> details = new ArrayList<>();
        String name = null;
        try {
            details = saRemoveSOserivice.getMembersToRemove();
            name = saSerivice.getSuperadminName(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getEmpDP(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        ShowOrganizer so = new ShowOrganizer(name,tokennObj.getUserType(token), path);
        details.add(0,so);

        Gson gson = new Gson();
        String saobj =gson.toJson(details);
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

        ShowOrganizer basicUser;
        Gson g = new Gson();
        basicUser = g.fromJson(body, ShowOrganizer.class);
        basicUser.setUserType(5);

        System.out.println(basicUser);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

        SARemoveSOService saRemoveSOserivice=new SARemoveSOService();
        SAdashboardService saSerivice=new SAdashboardService();
        ArrayList<ShowOrganizer> details = new ArrayList<>();
        boolean updated = false;
        String name = null;

        try {
            updated = saRemoveSOserivice.deleteMembers(basicUser, uid, userType);
            name = saSerivice.getSuperadminName(uid);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if(updated) {
            ShowOrganizer so = new ShowOrganizer(name, userType);
            details.add(0, so);

            Gson gson = new Gson();
            String saobj = gson.toJson(details);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }
        else{
            response.getWriter().println(404);
        }
    }
}
