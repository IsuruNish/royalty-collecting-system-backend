package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Respond;
import org.osca.model.SuperAdminDashboard;
import org.osca.model.SystemDetails;
import org.osca.service.ImageService;
import org.osca.service.SAchangeinfoService;
import org.osca.service.SAdashboardService;
import org.osca.service.SystemDetailsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SystemDetailsServlet", value = "/SystemDetailsServlet")
public class SystemDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";
        try {
            fname = fnameS.getSuperadminName(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        SystemDetailsService service = new SystemDetailsService();
        SystemDetails a = new SystemDetails();
        try {
            a.setCommision(service.getCommisionPercentage(a));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try { a.setCancellationDuration((int) service.getLicenseCancellationDuration(a));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
          a.setCancellationFee(service.getLicenseCancellationFee(a));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        a.setFname(fname);
        a.setDPpath(path);
        a.setUtype(utype);

        Gson gson = new Gson();
        String obj =gson.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(obj);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String typeOfRequest = request.getContentType().substring(0, 9);

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

        SystemDetails detail;
        Gson gson = new Gson();
        detail = gson.fromJson(body, SystemDetails.class);
        SystemDetailsService service = new SystemDetailsService();

        boolean changed = false;
        if(detail.getSystemDetailType() == 1){
            try {
                changed = service.changeCommisionPercentage(detail);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(detail.getSystemDetailType() == 2){
            try {
                changed = service.changeLicenseCancellationDuration(detail);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(detail.getSystemDetailType() == 3){
            try {
                changed =  service.changeLicenseCancellationFee(detail);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        Gson g = new Gson();
        String res;

        if (changed){
            res = g.toJson(new Respond(1));
        }else{
            res = g.toJson(new Respond(0));
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(res);
    }
}
