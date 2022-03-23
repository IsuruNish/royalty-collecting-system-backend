package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
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

@WebServlet(name = "SOPastEventsServlet", value = "/SOPastEventsServlet")
public class SOPastEventsServlet extends HttpServlet {
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


        try {
            path = dp.getUserDP(uid);
            fname = fnameS.getShowOrganizerName(uid);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        UpcomingEventsService nService = new UpcomingEventsService();
        ArrayList<ArrayList<String>> events = null;

        try {
            events = nService.getPastEventsSO(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        UpcomingEvents notify = new UpcomingEvents();
        notify.setFname(fname);
        notify.setDPpath(path);
        notify.setDetails(events);
        notify.setUt(utype);

        Gson g = new Gson();
        String sa;

        sa = g.toJson(notify);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
