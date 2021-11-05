package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.License;
import org.osca.model.Respond;
import org.osca.service.ApplyLicenseService;
import org.osca.service.ImageService;
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

@WebServlet(name = "ApplyLicenseServlet", value = "/ApplyLicenseServlet")
public class ApplyLicenseServlet extends HttpServlet {
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
            path = dp.getUserDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SAdashboardService fnameS = new SAdashboardService();
        String fname = "";
        try {
            fname = fnameS.getShowOrganizerName(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ApplyLicenseService songs = new ApplyLicenseService();
        ArrayList<ArrayList<Integer>> songIds = new ArrayList<>();
        ArrayList<String> songNames = new ArrayList<>();
        ArrayList<Integer> songYears = new ArrayList<>();
        ArrayList<ArrayList<String>> fNames= new ArrayList<>();
        ArrayList<ArrayList<String>> lNames= new ArrayList<>();

        try {
            songIds = songs.getIDsSongs();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            songNames = songs.getSongs(songIds.get(0));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            songYears = songs.getYears(songIds.get(0));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            fNames = songs.getFirstNames(songIds.get(1));
            System.out.println(fNames);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            lNames = songs.getlastNames(songIds.get(1));
            System.out.println(lNames);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        License a = new License();
        a.setDPpath(path);
        a.setUtype(utype);
        a.setFname(fname);
        a.setfNames(fNames);
        a.setlNames(lNames);
        a.setSongYears(songYears);
        a.setSongNames(songNames);
        a.setSongTempIds(songIds.get(0));
        a.setSongIds(songIds.get(1));

        System.out.println(a);
        Gson gson = new Gson();
        String saobj =gson.toJson(a);
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


        int uid = tokennObj.getUserID(token);
        int ut = tokennObj.getUserType(token);

        Gson gson = new Gson();
        License detail = gson.fromJson(body, License.class);

        ApplyLicenseService service = new ApplyLicenseService();

        System.out.println(detail);
        boolean changed = false;
        double commisionPercentage = 0;
        int number = 0;

        System.out.println(detail.getRequestType());
        if (detail.getRequestType() == 2) {
            double feePerSong = 5000;
            double commision = 0;

            //for Request perpose (not relevant for this servlet)
            int forSingers = 0;
            int forComposers = 1;
            int forWritters = 1;

            int songNo = detail.getSongIds().size();

            try {
                commisionPercentage = service.getLicenseCommisonPercentage();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            double totFee = feePerSong * songNo;
            commision = totFee * commisionPercentage;
            totFee = totFee + commision;

            try {
                changed = service.setCloseConcertInfo(detail, uid, commision, totFee, totFee - commision, songNo);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            if (changed) {
                try {
                    changed = service.setCloseConcertSongInfo(detail, uid);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    number = service.getCloseConcertID(detail, uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        else if(detail.getRequestType() == 1){
            double fee = 5000;
            try {
                changed = service.setOpenConcertInfo(detail, uid, fee);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            try {
                number = service.getOpenConcertID(detail, uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        Gson g = new Gson();
        String res;
        Respond a = new Respond();

        if (changed && number != 0){
            a.setOk(1);
            a.setNumbers(number);
            res = g.toJson(a);
        }else{
            a.setOk(0);
            res = g.toJson(a);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(res);
    }
}
