package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.ChangeInfoUsers;
import org.osca.model.ShowOrganizer;
import org.osca.service.AllUsersChangeInfoService;
import org.osca.model.ChangeInfo;
import org.osca.service.ImageService;
import org.osca.service.SAchangeinfoService;

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

@MultipartConfig
@WebServlet(name = "AllUsersChangeInfoServlet", value = "/AllUsersChangeInfoServlet")
public class AllUsersChangeInfoServlet extends HttpServlet {
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

        ArrayList<String> details = new ArrayList<>();
        ImageService dp = new ImageService();

//        if (userType == 2 || userType == 3){
//            SAchangeinfoService SAserivice=new SAchangeinfoService();
//
//            try {
//                details = SAserivice.getCIDetails(uid);
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
//
//            String path = null;
//            try {
//                path = dp.getEmpDP(uid);
//
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
//
//            SuperAdminDashboard sa = new SuperAdminDashboard(
//                    Integer.parseInt(details.get(5)),
//                    details.get(0),
//                    details.get(1),
//                    details.get(3),
//                    details.get(2),
//                    details.get(4),
//                    path);
//
//            System.out.println(sa);
//            Gson gson = new Gson();
//            System.out.println(sa);
//            String saobj =gson.toJson(sa);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(saobj);
//        }

        if (userType == 4){
            AllUsersChangeInfoService serivice = new AllUsersChangeInfoService();
            try {
                details = serivice.getDetailsOfMember(uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            String path = null;
            try {
                path = dp.getMemDP(uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            ChangeInfo sa = new ChangeInfo(
                    Integer.parseInt(details.get(8)),
                    details.get(3),
                    details.get(0),
                    details.get(1),
                    details.get(2),
                    details.get(4),
                    path,
                    details.get(6),
                    details.get(7),
                    details.get(5));

            Gson gson = new Gson();
            String saobj =gson.toJson(sa);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }

        else{
            AllUsersChangeInfoService serivice = new AllUsersChangeInfoService();
            try {
                details = serivice.getDetailsOfShowOrganizer(uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            String path = null;
            try {
                path = dp.getUserDP(uid);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            ChangeInfo sa = new ChangeInfo(
                    Integer.parseInt(details.get(5)),
                    details.get(0),
                    details.get(1),
                    details.get(3),
                    details.get(2),
                    details.get(4),
                    path);

            Gson gson = new Gson();
            String saobj =gson.toJson(sa);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(saobj);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        System.out.println("jirjhfgbrmskjfnerhjbfver");

        if (userType == 4){
            System.out.println("1");

            if (typeOfRequest.equals("multipart")) {
                System.out.println("2");

                Part p = request.getPart("file");
                if (p != null) {
                    p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");
                    File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");

                    String url = null;
                    CloudinaryImage obj = new CloudinaryImage();
                    url = obj.storeImage(file);
                    AllUsersChangeInfoService saService = new AllUsersChangeInfoService();

                    try {
                        done = saService.updateImagePathMember(uid, url);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    ifType = 0;
                }
            } else {

                String body = data.getBody(request);
                String requestType = body.substring(9, 10);
                AllUsersChangeInfoService service = new AllUsersChangeInfoService();

                if (Integer.parseInt(requestType) == 1) {
                    try {
                        done = service.daleteImagePathMember(uid);

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
                    done = changePassword(body,uid, userType);
                    System.out.println(done);
                    ifType = 3;
                }

                else if (Integer.parseInt(requestType) == 9){
                    System.out.println("ji");
                    done = changeBankDetails(body,uid);
                    System.out.println("ji");

                    System.out.println(done);
                    ifType = 2;
                }
            }

            if (done){
                if(ifType == 2){
                    AllUsersChangeInfoService SAserivice=new AllUsersChangeInfoService();
                    ArrayList<String> details = new ArrayList<>();


                    try {
                        details = SAserivice.getDetailsOfMember(uid);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }

                    ChangeInfo sa = new ChangeInfo(
                            Integer.parseInt(details.get(8)),
                            details.get(3),
                            details.get(0),
                            details.get(1),
                            details.get(2),
                            details.get(4),
                            details.get(6),
                            details.get(7),
                            details.get(5));


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

        else{
            if (typeOfRequest.equals("multipart")) {
                Part p = request.getPart("file");
                if (p != null) {
                    p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");
                    File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.jpg");

                    String url = null;
                    CloudinaryImage obj = new CloudinaryImage();
                    url = obj.storeImage(file);
                    AllUsersChangeInfoService saService = new AllUsersChangeInfoService();

                    try {
                        done = saService.updateImagePathShowOrganizer(uid, url);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                    ifType = 0;
                }
            } else {
                String body = data.getBody(request);
                String requestType = body.substring(9, 10);
                AllUsersChangeInfoService service = new AllUsersChangeInfoService();
                if (Integer.parseInt(requestType) == 1) {
                    try {
                        done = service.daleteImagePathShowOrganizer(uid);

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
                    done = changePassword(body,uid, userType);
                    System.out.println(done);
                    ifType = 3;
                }
            }

            if (done){
                if(ifType == 2){
                    AllUsersChangeInfoService SAserivice=new AllUsersChangeInfoService();
                    ArrayList<String> details = new ArrayList<>();


                    try {
                        details = SAserivice.getDetailsOfShowOrganizer(uid);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }

                    ChangeInfo sa = new ChangeInfo(
                            Integer.parseInt(details.get(5)),
                            details.get(0),
                            details.get(1),
                            details.get(3),
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

    }



    public boolean changePersonalInfo(String body, int userType, int  uid) throws IOException, SQLException, ClassNotFoundException {
        ChangeInfo user;
        Gson g = new Gson();
        user = g.fromJson(body, ChangeInfo.class);
        user.setUtype(userType);

        if (userType == 5){
            AllUsersChangeInfoService saService = new AllUsersChangeInfoService();
            SAchangeinfoService sService = new SAchangeinfoService();
            boolean updated = false;

            try {
                updated = saService.updateShowOrganizer(uid, user);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }


            boolean done = false;
            if (user.getEmailFlag() == 1){
                done = sService.setEmailVerificationForShowOrganizer(uid);
            }

            return updated && done;
        }

        else{
            AllUsersChangeInfoService saService = new AllUsersChangeInfoService();
            SAchangeinfoService sService = new SAchangeinfoService();
            boolean updated = false;

            try {
                updated = saService.updateMember(uid, user);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            boolean done = false;
            if (user.getEmailFlag() == 1){
                done = sService.setEmailVerificationForMember(uid);
            }

            return updated && done;
        }


    }

    public boolean changePassword(String body, int  uid, int userType) throws IOException {
        ChangeInfoUsers user;
        Gson g = new Gson();
        user = g.fromJson(body, ChangeInfoUsers.class);

        if (userType == 5){
            AllUsersChangeInfoService saService = new AllUsersChangeInfoService();
            boolean updated = false;

            try {
                updated = saService.updatePassShowOrganizer(uid, user.getPass(), user.getNewPass());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            return updated;
        }
        else{
            AllUsersChangeInfoService saService = new AllUsersChangeInfoService();
            boolean updated = false;

            try {
                updated = saService.updatePassMember(uid, user.getPass(), user.getNewPass());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            return updated;
        }

    }

    public boolean changeBankDetails(String body, int  uid) throws IOException {
        ChangeInfo user;
        Gson g = new Gson();
        user = g.fromJson(body, ChangeInfo.class);

        AllUsersChangeInfoService saService = new AllUsersChangeInfoService();
        boolean updated = false;

        try {
            updated = saService.updateBankDetails(uid, user.getAccNo(),user.getBankName(), user.getBankBranch() );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return updated;
    }
}
