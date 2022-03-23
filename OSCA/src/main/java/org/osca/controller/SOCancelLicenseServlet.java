package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.*;
import org.osca.service.ImageService;
import org.osca.service.SOCancelLicenseService;
import org.osca.service.ShowOrganizerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SOCancelLicenseServlet", value = "/SOCancelLicenseServlet")
public class SOCancelLicenseServlet extends HttpServlet {
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
        //int uid = 10001;
        SOCancelLicenseService service = new SOCancelLicenseService();
        ArrayList<ArrayList<String>> details = new ArrayList<>();
        try {
            details = service.getConcertDetails(uid);
//            System.out.println(details);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ShowOrganizerService SOserivice=new ShowOrganizerService();
        String fName=null;
        try {
             fName = SOserivice.getDashboardDetails(uid).get(1);
            System.out.println(fName);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getUserDP(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SOCancelLicense cl = new SOCancelLicense(
                details.get(0),
                details.get(1),
                details.get(2),
                details.get(3),
                details.get(4),
                details.get(5));




        Gson gson = new Gson();
        System.out.println(cl);
        cl.setUtype(userType);
        cl.setDpPath(path);
        cl.setFname(fName);
        String saobj =gson.toJson(cl);
        System.out.println(saobj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi");

        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String body = data.getBody(request);
        String token = header.substring(7);
        System.out.println(body);
        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int uid = tokennObj.getUserID(token);


        ConcertIDModel concertIDModel;
        Gson gson = new Gson();
       concertIDModel = gson.fromJson(body, ConcertIDModel.class);
        SOCancelLicenseService service = new SOCancelLicenseService();

        try {
            service.getID(concertIDModel);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


    }
}
