package org.osca.controller;

import com.google.gson.Gson;

import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;

import org.osca.model.SuperAdminDashboard;
import org.osca.service.ImageService;
import org.osca.service.SAchangeinfoService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
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
                1,
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




        Part p = request.getPart("file");
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

        if (p != null) {
            p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");
            File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");

            String url = null;
            CloudinaryImage obj = new CloudinaryImage();
            url = obj.storeImage(file);
            System.out.println(url);
            SAchangeinfoService saService = new SAchangeinfoService();
            boolean updated = false;

            try {
                updated = saService.updateImagePath(uid, url);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }



//        System.out.println("q");
//        System.out.println(q);
//
////        System.out.println(p);
//        Images dp = new Images();
//        dp.storeImage(request);
//        CloudinaryImage obj = new CloudinaryImage();
//        String url = null;
//        for (Part p : request.getParts()) {
//            System.out.println(p.getSubmittedFileName());
//            System.out.println(p.getClass().getSimpleName());
////            System.out.println(((File) p).getClass().getSimpleName());
//            System.out.println(p.getInputStream());
////            url = obj.storeImage((File) p);
//        }
//
//        System.out.println(url);
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(1);









        //Storing personal information in the database
//        if (changePersonalInfo(request,response)){
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(1);
//
//        }
//        else{
//            response.getWriter().println(0);
//        }








//        HeaderAndBody data = new HeaderAndBody();
//        String header = data.getAuthenticationHeader(request);
//        String body = data.getBody(request);
//        String token = header.substring(7);
//
//        ShowOrganizer basicUser;
//        Gson g = new Gson();
//        basicUser = g.fromJson(body, ShowOrganizer.class);
//        basicUser.setUserType(5);
//
//        String[] names = basicUser.getFname().split(" ");
//        basicUser.setFname(names[0]);
//        basicUser.setLname(names[1]);
//
//        JWebToken tokennObj = null;
//        try {
//            tokennObj = new JWebToken(token);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        String fname = tokennObj.getFirstName(token);
//        String lname = tokennObj.getLastName(token);
//        String email = tokennObj.getEmail(token);
//        int userType = tokennObj.getUserType(token);
//
//        SARemoveSOService saRemoveSOserivice=new SARemoveSOService();
//        ArrayList<ShowOrganizer> details = new ArrayList<>();
//        boolean updated = false;
//        try {
//            updated = saRemoveSOserivice.deleteShowOrganizers(basicUser, fname, lname, email, userType);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        if(updated) {
//            ShowOrganizer so = new ShowOrganizer(fname, 1);
//            details.add(0, so);
//
//            Gson gson = new Gson();
//            String saobj = gson.toJson(details);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(saobj);
//        }
//        else{
//            response.getWriter().println(404);
//        }
    }

    public boolean changePersonalInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int userType = tokennObj.getUserType(token);

        SuperAdminDashboard user;
        Gson g = new Gson();
        user = g.fromJson(body, SuperAdminDashboard.class);
        user.setUtype(userType);
        System.out.println("uefbrsjhfbskruhfskurghsrejtbsrf");

        System.out.println(user);
        SAchangeinfoService saService = new SAchangeinfoService();
        boolean updated = false;

        try {
            updated = saService.updateUser(uid, user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
