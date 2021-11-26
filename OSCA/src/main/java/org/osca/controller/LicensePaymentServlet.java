package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.License;
import org.osca.model.Notification;
import org.osca.model.Respond;
import org.osca.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import java.util.Arrays;
import java.util.List;

@MultipartConfig
@WebServlet(name = "LicensePaymentServlet", value = "/LicensePaymentServlet")
public class LicensePaymentServlet extends HttpServlet {
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
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        SAdashboardService fnameS = new SAdashboardService();
        ShowOrganizerService SOserivice=new ShowOrganizerService();
        String fname = "";
        String fullName = "";
        ArrayList<String> SOdetails = new ArrayList<>();

        try {
            fname = fnameS.getShowOrganizerName(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            fullName = fnameS.getShowOrganizerFULLName(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        try {
            SOdetails = SOserivice.getDashboardDetails(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        System.out.println(SOdetails);

        License a = new License();
        a.setFname(fname);
        a.setLname(SOdetails.get(2));
        a.setEmail(SOdetails.get(3));
        a.setPhoen(SOdetails.get(4));
        a.setDPpath(path);
        a.setUtype(utype);
        a.setUid(uid);

        System.out.println(a);
        Gson gson = new Gson();
        String saobj = gson.toJson(a);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfRequest = request.getContentType().substring(0, 9);

        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
//        String body = data.getBody(request);
        String token = header.substring(7);

        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int uid = tokennObj.getUserID(token);
        int ut = tokennObj.getUserType(token);

        System.out.println(typeOfRequest);
        License a = new License();
        Gson gson = new Gson();
        ApplyLicenseService service = new ApplyLicenseService();

        if (!typeOfRequest.equals("multipart")) {
            String body = data.getBody(request);
            License detail = gson.fromJson(body, License.class);

            if (detail.getRequestType() == 3) {

                double x = 0;

                try {
                    x = service.getTotalFee(detail.getConcertID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                a.setTotalFee(x);
                System.out.println(a);
                gson = new Gson();
                String saobj = gson.toJson(a);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }

            else if (detail.getRequestType() == 2){
                boolean x = false;
                try {
                    x = service.setPaymentSuccessful(detail.getConcertID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                Respond res = new Respond();

                if (x){
                    res.setOk(1);
                }
                else{
                    res.setOk(0);
                }
                System.out.println(res);

                gson = new Gson();
                String saobj = gson.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);

            }
        } else {
            int concertID = Integer.parseInt(request.getParameter("concertID").substring(1, request.getParameter("concertID").length()-1));
            a.setConcertID(concertID);

            NotificationService nService = new NotificationService();
            SAdashboardService saService = new SAdashboardService();

            Part p = request.getPart("file");
            if (p != null) {
                p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");
                File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");

                boolean  done = false;

                String url = null;
                CloudinaryImage obj = new CloudinaryImage();
                url = obj.storeImage(file);

                try {
                    done = service.setPaymentSlip(a.getConcertID(), url);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                String fullname = null;

                try {
                    fullname = saService.getShowOrganizerFULLName(uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                if (done){
                    try {
                        done = nService.setNotificationLicenseRequest(uid, fullname + " has sent a request for a license application");
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }


                Respond res = new Respond();

                if (done){
                    res.setOk(1);
                }
                else{
                    res.setOk(0);
                }

                gson = new Gson();
                String saobj = gson.toJson(res);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);

            }
        }


    }
}
