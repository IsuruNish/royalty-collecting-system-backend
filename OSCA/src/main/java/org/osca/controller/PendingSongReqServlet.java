package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Respond;
import org.osca.model.SongModification;
import org.osca.model.SongOwnerhip;
import org.osca.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "PendingSongReqServlet", value = "/PendingSongReqServlet")
public class PendingSongReqServlet extends HttpServlet {
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

        PendingSongReqService service = new PendingSongReqService();
        ImageService dp = new ImageService();
        SAdashboardService fnameS = new SAdashboardService();
        String path = null;
        String fname = null;
        ArrayList<ArrayList<String>> songs = new ArrayList<>();

        if (userType == 4) {
            try {
                path = dp.getMemDP(uid);
                fname = fnameS.getMemberName(uid);
                songs = service.getPendingReqSongDetails(userType, uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else if (userType == 3) {
            try {
                path = dp.getEmpDP(uid);
                fname = fnameS.getSuperadminName(uid);
                songs = service.getPendingReqSongDetails(userType, uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }


        Gson gson = new Gson();
        SongModification sa = new SongModification(songs, fname, path, userType);

        String saobj = gson.toJson(sa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfRequest = request.getContentType().substring(0, 9);
        System.out.println(typeOfRequest);

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
        int ut = tokennObj.getUserType(token);

        String body = data.getBody(request);
        SongOwnerhip obj;
        Gson gson = new Gson();
        obj = gson.fromJson(body, SongOwnerhip.class);
        System.out.println(obj);

        if (obj.getReqType() == 100) {
            ArrayList<String> info = new ArrayList<>();
            ArrayList<ArrayList<String>> singers = new ArrayList<>();
            ArrayList<ArrayList<String>> composers = new ArrayList<>();
            ArrayList<ArrayList<String>> writers = new ArrayList<>();

            PendingSongReqService songs = new PendingSongReqService();

            try {
                info = songs.getSongDetails(obj.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                singers = songs.getSingers(obj.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                composers = songs.getComposers(obj.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                writers = songs.getWriters(obj.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            SongOwnerhip a = new SongOwnerhip();
            a.setPOSTsingers(singers);
            a.setPOSTcomposers(composers);
            a.setPOSTwriters(writers);
            a.setPOSTinfo(info);

            System.out.println(a);
            Gson gson2 = new Gson();
            String saobj = gson2.toJson(a);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }

        else if (obj.getReqType() == 200) {
            PendingSongReqService songs = new PendingSongReqService();
            boolean done = false;

            try {
                done = songs.cancelRequest(obj.getSongID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            Respond a = new Respond();

            if (done){
                a.setOk(1);
            }
            else{
                a.setOk(0);
            }

            Gson gson2 = new Gson();
            String saobj = gson2.toJson(a);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }
    }
}
