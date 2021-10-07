//package org.osca.controller.image;
//
//import org.osca.controller.auth.JWebToken;
//import org.osca.controller.httpRequest.HeaderAndBody;
//import org.osca.service.ImageService;
//import org.osca.service.SAchangeinfoService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.Part;
//import java.io.File;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 1,
//        maxFileSize = 1024 * 1024 * 10,
//        maxRequestSize = 1024 * 1024 * 100
//)
//public class Images {
//
//    public boolean containImage(HttpServletRequest request) throws IOException, ServletException {
//        boolean has = false;
//        for (Part p : request.getParts()) {
//            has = true;
//        }
//        return has;
//    }
//
//
//    public void storeImage(HttpServletRequest request) throws IOException, ServletException {
////        HeaderAndBody data = new HeaderAndBody();
////        String header = data.getAuthenticationHeader(request);
////        String token = header.substring(7);
////        JWebToken tokennObj = null;
////        try {
////            tokennObj = new JWebToken(token);
////        } catch (NoSuchAlgorithmException e) {
////            e.printStackTrace();
////        }
////        String fname = tokennObj.getFirstName(token);
////        String lname = tokennObj.getLastName(token);
////        String email = tokennObj.getEmail(token);
////
////        SAchangeinfoService SAserivice=new SAchangeinfoService();
////        String empid = null;
////
////        try {
////            empid = String.valueOf(SAserivice.getEmpID(fname,lname,email));
////        } catch (SQLException throwables) {
////            throwables.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////
////
////        System.out.println("hello");
////        for (Part p : request.getParts()) {
////            String extension = p.getSubmittedFileName().substring(p.getSubmittedFileName().length()-3);
//////            System.out.println(extension);
//////            File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\"+ empid +"."+ extension);
//////            file.delete();
////            p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\" + empid +"."+ extension);
////            System.out.println("done?");
//////            http://localhost:8080/OSCA_war_exploded/ProfilePhotos/" + picname;
////
////        }
//    }
//
//    public String getImagePath(int uid) throws SQLException, ClassNotFoundException {
//
//
//        return dp;
//
//
//    }
//}
