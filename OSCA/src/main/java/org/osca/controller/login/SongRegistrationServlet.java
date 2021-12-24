package org.osca.controller.login;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.*;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MultipartConfig
@WebServlet(name = "SongRegistrationServlet", value = "/SongRegistrationServlet")
public class SongRegistrationServlet extends HttpServlet {
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
            path = dp.getMemDP(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        SongRegistrationService songReg = new SongRegistrationService();
        ArrayList<ArrayList<String>> details = new ArrayList<>();
        try {
            details = songReg.getMemberDetails();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        SAdashboardService fnameService = new SAdashboardService();
        String fname = null;
        try {
            fname = fnameService.getMemberName(uid);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        MemberDashboard mem = new MemberDashboard(utype,details.get(0),details.get(1), path, fname);

        Gson gson = new Gson();
        String saobj =gson.toJson(mem);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(saobj);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfRequest = request.getContentType().substring(0, 9);
        System.out.println(typeOfRequest);

        HeaderAndBody data = new HeaderAndBody();
        String header = data.getAuthenticationHeader(request);
        String token = header.substring(7);
        Boolean done = true;
        JWebToken tokennObj = null;
        try {
            tokennObj = new JWebToken(token);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert tokennObj != null;
        int uid = tokennObj.getUserID(token);
        int ut = tokennObj.getUserType(token);

        Songs song = new Songs();
        song.setMemSingers(new Gson().fromJson( request.getParameter("memSingers"), List.class ));
        song.setMemComposers(new Gson().fromJson( request.getParameter("memComposers"), List.class ));
        song.setMemWritters(new Gson().fromJson( request.getParameter("memWritters"), List.class ));
        song.setNOmemSingers(new Gson().fromJson( request.getParameter("NOmemSingers"), List.class ));
        song.setNOmemComposers(new Gson().fromJson( request.getParameter("NOmemComposers"), List.class ));
        song.setNOmemWritters(new Gson().fromJson( request.getParameter("NOmemWritters"), List.class ));
        song.setInfo(new Gson().fromJson( request.getParameter("info"), List.class ));

        System.out.println(song);

        System.out.println("a");

        Part p = request.getPart("file");
        System.out.println("b");

        if (p != null) {
            System.out.println("c");

            p.write("src\\main\\webapp\\ProfilePhotos\\1000.pdf");
//            p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");
            System.out.println("d");

            File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");
            System.out.println("e");

            String url = null;
            String NEWurl = null;
            int tempSongID = 0;
            CloudinaryImage obj = new CloudinaryImage();
            url = obj.storeImage(file);
            SongRegistrationService songService = new SongRegistrationService();

            try {
                done = songService.storeSongDetails(uid, ut,song,url);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("0");

            try {
                NEWurl = songService.makeDownloadableURL(url);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("1");

            try {
                tempSongID = songService.getTempSongID(NEWurl);

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("2");

            try {
                done = songService.addMemberSingers(tempSongID, song.getMemSingers(), "M");

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("3");

            try {
                done = songService.addMemberComposers(tempSongID, song.getMemComposers(),"M");

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("4");

            try {
                done = songService.addMemberWritters(tempSongID, song.getMemWritters(),"M");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
//            ArrayList<ArrayList<String>> AllNoneMemberMDetails = new ArrayList<>();
//
//            try {
//                AllNoneMemberMDetails = songService.checkNoneMemberInDB();
//            } catch (SQLException | ClassNotFoundException throwables) {
//                throwables.printStackTrace();
//            }
//
//            ArrayList<String> idsMem = AllNoneMemberMDetails.get(0);
//            ArrayList<String> fnamesMem  = AllNoneMemberMDetails.get(1);
//            ArrayList<String> lnamesMem = AllNoneMemberMDetails.get(2);
            ArrayList<List<String>> idList = new ArrayList<>();
            System.out.println("5");

            try {
                idList = giveNonMembersInDB(song.getNOmemSingers(), song.getNOmemComposers(),song.getNOmemWritters(), tempSongID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println(idList);
            System.out.println("6");

            try {
                done =songService.addMemberSingers(tempSongID, idList.get(0),"N");

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("7");

            try {
                done = songService.addMemberComposers(tempSongID, idList.get(1),"N");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("8");

            try {
                done = songService.addMemberWritters(tempSongID, idList.get(2),"N");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            NotificationService nService = new NotificationService();
            SAdashboardService saService = new SAdashboardService();
            String fullname = null;
            System.out.println("9");

            try {
                fullname = saService.getMemberFULLName(uid);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("last ME");

            if (done){
                try {
                    done = nService.setNotificationSongRegRequest(ut-1, fullname + " has sent a request for a song registration");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        Gson g = new Gson();
        String res;
        if (done){
            res = g.toJson(new Respond(1));
        }
        else{
            res = g.toJson(new Respond(0));
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().println(res);

    }



    public ArrayList<List<String>> giveNonMembersInDB(List<String> temp1, List<String> temp2, List<String> temp3, int songID) throws SQLException, ClassNotFoundException {
        ArrayList<ArrayList<String>> AllNoneMemberMDetails = new ArrayList<>();
        SongRegistrationService songService = new SongRegistrationService();
        try {
            AllNoneMemberMDetails = songService.checkNoneMemberInDB();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        ArrayList<String> idsMem = AllNoneMemberMDetails.get(0);
        ArrayList<String> fnamesMem  = AllNoneMemberMDetails.get(1);
        ArrayList<String> lnamesMem = AllNoneMemberMDetails.get(2);
        ArrayList<List<String>> idList = new ArrayList<>();


        List<String> sIDS = new ArrayList<>();
        List<String> cIDS = new ArrayList<>();
        List<String> wIDS = new ArrayList<>();

        for (String s : temp1) {
            String[] k = s.split(" ");
            int flag = 0;
            int id = 0;
            for (int j = 0; j < idsMem.size(); j++) {

                if (k[0].equals(fnamesMem.get(j)) && k[1].equals(lnamesMem.get(j))) {
                    sIDS.add(idsMem.get(j));
                    flag = 1;
                    break;
                }
            }

            if (flag == 0){
                songService.addNoneMem(k[0], k[1]);
                id = songService.getMemberID(k[0], k[1]);
                sIDS.add(String.valueOf(id));
            }
        }




        try {
            AllNoneMemberMDetails = songService.checkNoneMemberInDB();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        idsMem = AllNoneMemberMDetails.get(0);
        fnamesMem  = AllNoneMemberMDetails.get(1);
        lnamesMem = AllNoneMemberMDetails.get(2);

        System.out.println(fnamesMem);
        System.out.println(lnamesMem);


        for (String s : temp2) {
            String[] k = s.split(" ");
            int flag = 0;
            int id = 0;
            for (int j = 0; j < idsMem.size(); j++) {
                if (k[0].equals(fnamesMem.get(j)) && k[1].equals(lnamesMem.get(j))) {
                    System.out.println(k[0]);
                    System.out.println(k[1]);
                    cIDS.add(idsMem.get(j));
                    flag = 1;
                    break;
                }
            }
            System.out.println(flag);

            if (flag == 0){
                songService.addNoneMem(k[0], k[1]);
                id = songService.getMemberID(k[0], k[1]);
                cIDS.add(String.valueOf(id));
            }
        }




        try {
            AllNoneMemberMDetails = songService.checkNoneMemberInDB();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        idsMem = AllNoneMemberMDetails.get(0);
        fnamesMem  = AllNoneMemberMDetails.get(1);
        lnamesMem = AllNoneMemberMDetails.get(2);

        for (String s : temp3) {
            String[] k = s.split(" ");
            int flag = 0;
            int id = 0;
            for (int j = 0; j < idsMem.size(); j++) {

                if (k[0].equals(fnamesMem.get(j)) && k[1].equals(lnamesMem.get(j))) {
                    wIDS.add(idsMem.get(j));
                    flag = 1;
                    break;
                }
            }

            if (flag == 0){
                songService.addNoneMem(k[0], k[1]);
                id = songService.getMemberID(k[0], k[1]);
                wIDS.add(String.valueOf(id));
            }
        }

        ArrayList<List<String>> tot = new ArrayList<>();
        tot.add(sIDS);
        tot.add(cIDS);
        tot.add(wIDS);

        return tot;
    }

//    public static void main(String[] args) throws IOException {
////        File file2 = new File("..\\..\\..\\..\\webapp\\ProfilePhotos\\abc.txt");
////
////        file2.createNewFile();
//        File file = new File("src\\main\\webapp\\ProfilePhotos\\abc.pdf");
//        file.createNewFile();
//
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
//        System.out.println("Current absolute path is: " + s);
//    }
}

