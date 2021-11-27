package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Requests;
import org.osca.model.Respond;
import org.osca.model.SongModification;
import org.osca.service.ImageService;
import org.osca.service.NotificationService;
import org.osca.service.SAdashboardService;
import org.osca.service.SongModificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SongModificationServlet", value = "/SongModificationServlet")
public class SongModificationServlet extends HttpServlet {
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

        SongModificationService service = new SongModificationService();
        ImageService dp = new ImageService();
        SAdashboardService fnameS = new SAdashboardService();
        String path = null;
        String fname = null;
        ArrayList<ArrayList<String>> songs = new ArrayList<>();

        if (userType == 4){
            try {
                path = dp.getMemDP(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                fname = fnameS.getMemberName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }


            try {
                songs = service.getSongDetails();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if(userType == 3){
            try {
                path = dp.getEmpDP(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                fname = fnameS.getSuperadminName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }


            try {
                songs = service.getSongDetails();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }


        Gson gson = new Gson();
        SongModification sa = new SongModification(songs,fname,path,userType);

        String saobj =gson.toJson(sa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("eufnekjfb");
        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);
        String token = header.substring(7);
        System.out.println("1");

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

        Gson gson = new Gson();
        SongModification detail = gson.fromJson(body, SongModification.class);

        SongModificationService service = new SongModificationService();
        NotificationService nService = new NotificationService();
        SAdashboardService fnameS = new SAdashboardService();
        boolean done = false;
        String  fname = null;

        try {
            done = service.sendSongDeleteRequest(userType,detail,uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        if (userType == 4){
            try {
                fname = fnameS.getMemberFULLName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (userType == 3){
            try {
                fname = fnameS.getSuperadminFULLName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            done = nService.setNotificationSongDelRequest(uid,userType-1,"Song delete request was sent by "+fname+" for the "+detail.getSongName()+" song");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        Respond res = new Respond();
        if (done){
            res.setOk(1);
        }
        else{
            res.setOk(0);
        }

        System.out.println(res);
        System.out.println("sssssssssssssssssssssssssssssssssssssssssssss");

        Gson gson2 = new Gson();
        String saobj =gson2.toJson(res);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }
}
