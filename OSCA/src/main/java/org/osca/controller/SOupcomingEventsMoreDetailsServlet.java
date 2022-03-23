package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.SongOwnerhip;
import org.osca.model.UpcomingEvents;
import org.osca.service.ImageService;
import org.osca.service.SAdashboardService;
import org.osca.service.UpcomingEventsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SOupcomingEventsMoreDetailsServlet", value = "/SOupcomingEventsMoreDetailsServlet")
public class SOupcomingEventsMoreDetailsServlet extends HttpServlet {
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

        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";


        if(utype == 5){
            try {
                path = dp.getUserDP(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                fname = fnameS.getShowOrganizerName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            try {
                path = dp.getEmpDP(uid);
                fname = fnameS.getSuperadminName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        UpcomingEvents a = new UpcomingEvents();
        a.setFname(fname);
        a.setDPpath(path);
        a.setUt(utype);

        Gson gson2 = new Gson();
        String saobj = gson2.toJson(a);
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

        int utype = tokennObj.getUserType(token);
        int uid = tokennObj.getUserID(token);

        Gson gson = new Gson();
        UpcomingEvents obj = gson.fromJson(body, UpcomingEvents.class);

        UpcomingEventsService s = new UpcomingEventsService();
        ArrayList<String> event = new ArrayList<>();

        try {
            event = s.getSongsForconcert(obj.getConcertID());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(event);
        UpcomingEvents a = new UpcomingEvents();
        a.setSongs(event);

        Gson gson2 = new Gson();
        String saobj = gson2.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }
}
