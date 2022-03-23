package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.Requests;
import org.osca.model.Respond;
import org.osca.service.ImageService;
import org.osca.service.NotificationService;
import org.osca.service.RequestsService;
import org.osca.service.SAdashboardService;

import javax.mail.MessagingException;
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
        NotificationService nService = new NotificationService();

        boolean done = false;

        if (detail.getReqType() == 1  && detail.getIsAccepted() == 1 && ut == 3){
            try {
                done = service.AcceptLicenseRequest(detail.getLicenseID(),type);
            } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                throwables.printStackTrace();
            }


            try {
                done = nService.setNotificationLicenseAccepted(2,"New license application for "+ detail.getConcertName()+" concert",1);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 1  && detail.getIsAccepted() == 1 && (ut == 2 || ut == 1)){
            try {
                done = service.AcceptLicenseRequest(detail.getLicenseID(),type);
            } catch (SQLException | ClassNotFoundException | MessagingException throwables) {
                throwables.printStackTrace();
            }

            ArrayList<ArrayList<String>> allDetails = new ArrayList<>();
            ArrayList<Integer> memIDs = new ArrayList<>();
            try {
                done = nService.setNotificationLicenseAccepted(detail.getShowOrganizerID(),"license application request for "+detail.getConcertName()+" is accepted",2);
                allDetails = nService.getAllSongIDandSongNames(detail.getLicenseID());

                for(int index = 0; index < allDetails.get(0).size(); index++){

                    memIDs = nService.sendAllNotificationsForRelaventMembers(Integer.parseInt(allDetails.get(0).get(index)));

                    for(Integer id: memIDs){
                        done = nService.setNotificationSongDelAccepted(id,allDetails.get(1).get(index)+" song will be used in the "+detail.getConcertName()+" Concert",2);
                    }
                }
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

            try {
                done = nService.setNotificationLicenseDenied(detail.getShowOrganizerID(),"license application request for "+ detail.getConcertName()+" is rejected");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }





        else if (detail.getReqType() == 2  && detail.getIsAccepted() == 1 && ut == 3){
            try {
                done = service.AcceptSongRegRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                done = nService.setNotificationSongRegAccepted(2, "Song registration request for "+ detail.getSongName()+" is accepted",1);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 2  && detail.getIsAccepted() == 1 && (ut == 2 || ut == 1)){
            try {
                done = service.AcceptSongRegRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                done = nService.setNotificationSongRegAccepted(detail.getMemberID(), "Song registration request for "+ detail.getSongName()+" song is accepted",2);
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

            try {
                done = nService.setNotificationSongRegDenied(detail.getMemberID(), "Song registration request for "+detail.getSongName()+" is rejected");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }





        else if (detail.getReqType() == 3  && detail.getIsAccepted() == 1 && ut == 3){
            try {
                done = service.AcceptSongOwnRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                System.out.println("awaaaaa");
                done =  nService.setNotificationSongOwnAccepted(2, "Song ownership change request for "+detail.getSongName()+" is accepted",1);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 3  && detail.getIsAccepted() == 1 && (ut == 2 || ut == 1)){
            try {
                done = service.AcceptSongOwnRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            //put id here and song name
            try {
                done =  nService.setNotificationSongOwnAccepted(detail.getMemberID(), "Song ownership change request for "+detail.getSongName()+" is accepted",2);
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

            //put id here and song name
            try {
                done = nService.setNotificationSongOwnDenied(detail.getMemberID(), "Song ownership change request for "+detail.getSongName()+" is rejected");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }



        //put id here and song name

        else if (detail.getReqType() == 4  && detail.getIsAccepted() == 1 && ut == 3){
            try {
                done = service.AccpetSongDelRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                done = nService.setNotificationSongDelAccepted(2, "Song removal request for "+detail.getSongName()+" is accepted",1);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        else if (detail.getReqType() == 4  && detail.getIsAccepted() == 1 && (ut == 2 || ut == 1)){
            try {
                done = service.AccpetSongDelRequest(detail.getSongID(),type);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                done = nService.setNotificationSongDelAccepted(detail.getMemberID(), "Song removal request for "+ detail.getSongName()+" is accepted",2);
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

            try {
                done = nService.setNotificationSongDelDenied(detail.getMemberID(), "Song removal request for "+detail.getSongName()+" is rejected");
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
