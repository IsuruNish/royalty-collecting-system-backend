package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Notification;
import org.osca.model.Respond;
import org.osca.service.ImageService;
import org.osca.service.NotificationService;
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

@WebServlet(name = "NotificationServlet", value = "/NotificationServlet")
public class NotificationServlet extends HttpServlet {
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

        if (utype == 4){
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
        }
        else if(utype == 5){
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
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                fname = fnameS.getSuperadminName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        NotificationService nService = new NotificationService();
        ArrayList<ArrayList<String>> msg = null;
        ArrayList<ArrayList<String>> msgREAD = null;

        if (utype == 4){
            try {
                msg = nService.getNotificationsUID(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

//            try {
//                msgREAD = nService.getREADNotificationsUID(uid);
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
        }
        else if(utype == 5){
            try {
                msg = nService.getNotificationsUID(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

//            try {
//                msgREAD = nService.getREADNotificationsUID(uid);
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
        }
        else if(utype == 3){
            try {
                msg = nService.getNotificationsUID(3);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

//            try {
//                msgREAD = nService.getREADNotificationsUtype(3);
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
        }
        else{
            try {
                msg = nService.getNotificationsUID(2);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
//            try {
//                msgREAD = nService.getREADNotificationsUtype(3);
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
        }

        int notifyNo = 0;
        if (utype == 5 || utype == 4){
            try {
                notifyNo = nService.newNotificationNumberUsingUID(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (utype == 1 || utype == 2 || utype == 3){
            try {
                notifyNo = nService.newNotificationNumberUsingUID(utype);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        Notification notify = new Notification();
        notify.setFname(fname);
        notify.setDpPath(path);
        notify.setMsg(msg);
        notify.setNumberOfNotifications(notifyNo);
        notify.setUid(uid);
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
        Notification detail = gson.fromJson(body, Notification.class);

        NotificationService nService = new NotificationService();
        boolean done = false;
        int notifyNo = 0;

        System.out.println(detail);

        if (detail.getRequestType() == 1 && (utype == 5 || utype == 4)){
            try {
                notifyNo = nService.newNotificationNumberUsingUID(uid);
                done = true;
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getRequestType() == 1 && (utype == 1 || utype == 2 || utype == 3)){
            int ut = 0;
            if(utype == 3){
                ut = 3;
            }
            else{
                ut = 2;
            }
            try {
                notifyNo = nService.newNotificationNumberUsingUID(ut);
                done = true;
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getRequestType() == 2){
            try {
                done = nService.deleteNotification(detail.getNotificationID());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        else if (detail.getRequestType() == 3){
            try {
                done = nService.readNotifications(detail.getUnreadIDs());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        Respond res = new Respond();

        if (done){
            res.setOk(1);
            res.setNumbers(notifyNo);
        }
        else{
            res.setOk(0);
        }

        System.out.println(notifyNo);
        System.out.println(res);

        Gson g = new Gson();
        String sa;

        sa = g.toJson(res);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(sa);

    }
}
