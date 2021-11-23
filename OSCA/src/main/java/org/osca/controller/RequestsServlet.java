package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Requests;
import org.osca.model.Respond;
import org.osca.service.ImageService;
import org.osca.service.RequestsService;
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

@WebServlet(name = "RequestsServlet", value = "/RequestsServlet")
public class RequestsServlet extends HttpServlet {
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

        RequestsService service = new RequestsService();
        ArrayList<ArrayList<String>> license1 = new ArrayList<>();
        ArrayList<ArrayList<String>> song1 = new ArrayList<>();
        ArrayList<ArrayList<String>> song2 = new ArrayList<>();
        ArrayList<ArrayList<String>> song3 = new ArrayList<>();
        ArrayList<String> info = new ArrayList<>();

        int type = -1;

        if(utype == 3 ){
            type = 0;
        }
        else{
            type = 1;
        }

        try {
            license1 = service.getLicenseAppReqData(type);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            song1 = service.getSongRegReqData(type);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            song2 = service.getSongOwnReqData(type);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            song3 = service.getSongDelReqData(type);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }



        info.add(fname);
        info.add(String.valueOf(utype));
        info.add(String.valueOf(uid));
        info.add(path);

        Requests req = new Requests();
        req.setLicenseAppReqs(license1);
        req.setInfo(info);
        req.setSongRegReq(song1);
        req.setSongOwnReq(song2);
        req.setSongDelReq(song3);

        Gson g = new Gson();
        String sa;

        sa = g.toJson(req);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);

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

        int type = -1;

        if(ut == 3 ){
            type = 1;
        }
        else{
            type = 2;
        }

        Gson gson = new Gson();
        Requests detail = gson.fromJson(body, Requests.class);

        System.out.println(detail);

        RequestsService service = new RequestsService();
        boolean done = false;

        if (detail.getReqType() == 1  && detail.getIsAccepted() == 1){
            try {
                done = service.AcceptLicenseRequest(detail.getLicenseID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getReqType() == 1  && detail.getIsAccepted() == 0){
            try {
                done = service.DenyLicenseRequest(detail.getLicenseID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getReqType() == 2  && detail.getIsAccepted() == 1){
            try {
                done = service.AcceptSongRegRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getReqType() == 2  && detail.getIsAccepted() == 0){
            try {
                done = service.DenySongRegRequest(detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getReqType() == 3  && detail.getIsAccepted() == 1){
            try {
                done = service.AcceptSongOwnRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 3  && detail.getIsAccepted() == 0){
            try {
                done = service.DenySongOwnRequest(detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getReqType() == 4  && detail.getIsAccepted() == 1){
            try {
                done = service.AccpetSongDelRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 4  && detail.getIsAccepted() == 0){
            try {
                done = service.DenySongDelRequest(detail.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        Respond theRes = new Respond();

        if (done){
            theRes.setOk(1);
        }
        else{
            theRes.setOk(0);
        }

        Gson g = new Gson();
        String sa;

        sa = g.toJson(theRes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);
    }
}
