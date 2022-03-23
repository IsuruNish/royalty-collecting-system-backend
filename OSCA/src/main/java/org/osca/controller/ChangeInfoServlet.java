package org.osca.controller;

import com.google.gson.Gson;

import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;

import org.osca.model.ChangeInfoUsers;
import org.osca.model.ShowOrganizer;
import org.osca.model.SuperAdminDashboard;
import org.osca.service.ImageService;
import org.osca.service.LoginService;
import org.osca.service.SAchangeinfoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)

@WebServlet(name = "ChangeInfoServlet", value = "/ChangeInfoServlet")
public class ChangeInfoServlet extends HttpServlet {
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

        SAchangeinfoService SAserivice=new SAchangeinfoService();
        ArrayList<String> details = new ArrayList<>();

        try {
            details = SAserivice.getCIDetails(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ImageService dp = new ImageService();
        String path = null;
        try {
            path = dp.getEmpDP(uid);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SuperAdminDashboard sa = new SuperAdminDashboard(
                Integer.parseInt(details.get(5)),
                details.get(0),
                details.get(1),
                details.get(3),
                details.get(2),
                details.get(4),
                path);

        System.out.println(sa);
        Gson gson = new Gson();
        System.out.println(sa);
        String saobj =gson.toJson(sa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        if (ServletFileUpload.isMultipartContent(request)) {
//            final FileItemFactory factory = new DiskFileItemFactory();
//            final ServletFileUpload upload = new ServletFileUpload(factory);
//
//            try {
//                final List items = upload.parseRequest(request);
//
//                for (Iterator itr = items.iterator(); itr.hasNext(); ) {
//                    final FileItem item = (FileItem) itr.next();
//
//                    if (!item.isFormField()) {
//
//                        String url = null;
//                        CloudinaryImage obj = new CloudinaryImage();
//                        url = obj.storeImage((File) item);
//                        System.out.println(url);
//                    }
//                }
//            } catch (FileUploadException e) {
//                e.printStackTrace();
//            }
//        }

        String typeOfRequest = request.getContentType().substring(0, 9);

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
        boolean done = false;
        int ifType = -1;

        if (typeOfRequest.equals("multipart")) {
            Part p = request.getPart("file");
            if (p != null) {
                p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");
                File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");

                String url = null;
                CloudinaryImage obj = new CloudinaryImage();
                url = obj.storeImage(file);
                SAchangeinfoService saService = new SAchangeinfoService();

                try {
                    done = saService.updateImagePath(uid, url);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                ifType = 0;
            }
        } else {
            String body = data.getBody(request);
            String requestType = body.substring(9, 10);
            SAchangeinfoService service = new SAchangeinfoService();
            if (Integer.parseInt(requestType) == 1) {
                try {
                    done = service.daleteImagePath(uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                ifType = 1;

            } else if (Integer.parseInt(requestType) == 2) {
                try {
                    done = changePersonalInfo("{" + body.substring(11), userType, uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                ifType = 2;

            }

            else if (Integer.parseInt(requestType) == 3){
                done = changePassword(body,uid);
                System.out.println(done);
                ifType = 3;

            }
        }

        if (done){
            if(ifType == 2){
                SAchangeinfoService SAserivice=new SAchangeinfoService();
                ArrayList<String> details = new ArrayList<>();


                try {
                    details = SAserivice.getCIDetails(uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                SuperAdminDashboard sa = new SuperAdminDashboard(
                        Integer.parseInt(details.get(5)),
                        details.get(3),
                        details.get(0),
                        details.get(1),
                        details.get(2),
                        details.get(4));


                System.out.println(sa);
                Gson g = new Gson();
                String res = g.toJson(sa);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().println(res);
            }
            else {
                ShowOrganizer RealUser = new ShowOrganizer(userType);
                Gson g = new Gson();
                String res = g.toJson(RealUser);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().println(res);
            }
        }
        else{

            ShowOrganizer RealUser = new ShowOrganizer(-1);
            Gson g = new Gson();
            String res = g.toJson(RealUser);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().println(res);
        }
    }



    public boolean changePersonalInfo(String body, int userType, int  uid) throws IOException, SQLException, ClassNotFoundException {
        SuperAdminDashboard user;
        Gson g = new Gson();
        user = g.fromJson(body, SuperAdminDashboard.class);
        user.setUtype(userType);

        SAchangeinfoService saService = new SAchangeinfoService();
        boolean updated = false;

        try {
            updated = saService.updateUser(uid, user);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        boolean done = false;
        if (user.getEmailFlag() == 1){
            done = saService.setEmailVerificationForOSCA(uid);
        }

        return updated && done;
    }

    public boolean changePassword(String body, int  uid) throws IOException {
        ChangeInfoUsers user;
        Gson g = new Gson();
        user = g.fromJson(body, ChangeInfoUsers.class);

        SAchangeinfoService saService = new SAchangeinfoService();
        boolean updated = false;

        try {
            updated = saService.updatePass(uid, user.getPass(), user.getNewPass());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updated;
    }

}
