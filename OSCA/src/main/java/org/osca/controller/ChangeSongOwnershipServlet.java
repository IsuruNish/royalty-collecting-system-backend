package org.osca.controller;

import com.google.gson.Gson;
import org.osca.controller.auth.JWebToken;
import org.osca.controller.httpRequest.CloudinaryImage;
import org.osca.controller.httpRequest.HeaderAndBody;
import org.osca.model.AAddUsers;
import org.osca.model.License;
import org.osca.model.Respond;
import org.osca.model.SongOwnerhip;
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
import java.util.List;

@MultipartConfig
@WebServlet(name = "ChangeSongOwnershipServlet", value = "/ChangeSongOwnershipServlet")
public class ChangeSongOwnershipServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HeaderAndBody data = new HeaderAndBody();
//        String header = data.getAuthenticationHeader(request);
//        String body = data.getBody(request);
//        String token = header.substring(7);
//
//        JWebToken tokennObj = null;
//        try {
//            tokennObj = new JWebToken(token);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        int utype = tokennObj.getUserType(token);
//        int uid = tokennObj.getUserID(token);
//
//        SongOwnerhip obj;
//        Gson gson = new Gson();
//        obj = gson.fromJson(body, SongOwnerhip.class);
//
//        ArrayList<String> info = new ArrayList<>();
//        ChangeSongOwnershipService songs = new ChangeSongOwnershipService();
//
//        try {
//            info = songs.getSongDetails(obj.getSongID());
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        License a = new License();
//        a.setDPpath(path);
//        a.setUtype(utype);
//        a.setFname(fname);
//        a.setfNames(fNames);
//        a.setlNames(lNames);
//        a.setSongYears(songYears);
//        a.setSongNames(songNames);
//        a.setSongTempIds(songIds.get(0));
//        a.setSongIds(songIds.get(1));
//
//        System.out.println(a);
//        Gson gson = new Gson();
//        String saobj =gson.toJson(a);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().println(saobj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfRequest = request.getContentType().substring(0, 9);
        System.out.println(typeOfRequest);

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
        int ut = tokennObj.getUserType(token);



        if (typeOfRequest.equals("multipart")) {
            System.out.println("typeOfRequest");

            SongOwnerhip song = new SongOwnerhip();

            song.setMemSingers(new Gson().fromJson(request.getParameter("memSingers"), List.class));
            song.setMemComposers(new Gson().fromJson(request.getParameter("memComposers"), List.class));
            song.setMemWritters(new Gson().fromJson(request.getParameter("memWritters"), List.class));
            song.setNOmemSingers(new Gson().fromJson(request.getParameter("NOmemSingers"), List.class));
            song.setNOmemComposers(new Gson().fromJson(request.getParameter("NOmemComposers"), List.class));
            song.setNOmemWritters(new Gson().fromJson(request.getParameter("NOmemWritters"), List.class));

//            song.setDelSinger(new Gson().fromJson(request.getParameter("delSinger"), List.class));
//            song.setDelComposers(new Gson().fromJson(request.getParameter("delComposers"), List.class));
//            song.setDelWritters(new Gson().fromJson(request.getParameter("delWritters"), List.class));
            song.setInfo(new Gson().fromJson(request.getParameter("info"), List.class));

            System.out.println("1");
            boolean done = false;
            Part p = request.getPart("file");
            if (p != null) {
                p.write("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");
                File file = new File("C:\\Users\\Asus\\Desktop\\be\\osca-royalty-collector-backend\\OSCA\\src\\main\\webapp\\ProfilePhotos\\1000.pdf");

                String url = null;
                String NEWurl = null;
                int tempSongID = 0;
                int currentID = 0;

                CloudinaryImage obj = new CloudinaryImage();
                url = obj.storeImage(file);
                ChangeSongOwnershipService songService = new ChangeSongOwnershipService();
                SongRegistrationService sService = new SongRegistrationService();

                try {
                    currentID = songService.getTempSongID(Integer.parseInt(song.getInfo().get(0)));
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    done = songService.storeSongDetails(uid, ut,song,url, currentID);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    NEWurl = sService.makeDownloadableURL(url);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    tempSongID = songService.getTempSongID(NEWurl);

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }


                //need to check//
                //need to check//
                //need to check//
                //need to check//
                //need to check//
                System.out.println("4");

                try {
//                    List<String> justMadeForFunctionCall = song.getMemSingers();
//                    justMadeForFunctionCall.addAll(song.getDelSinger());
                    done = songService.addMemberSingers(tempSongID, song.getMemSingers(), "M");

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("5");

                try {
//                    List<String> justMadeForFunctionCall = song.getMemComposers();
//                    justMadeForFunctionCall.addAll(song.getDelComposers());
                    done = songService.addMemberComposers(tempSongID, song.getMemComposers(),"M");

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("6");

                try {
//                    List<String> justMadeForFunctionCall = song.getMemWritters();
//                    justMadeForFunctionCall.addAll(song.getDelWritters());
                    done = songService.addMemberWritters(tempSongID, song.getMemWritters(),"M");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                ArrayList<List<String>> idList = new ArrayList<>();
                System.out.println("7");

                try {
                    idList = giveNonMembersInDB(song.getNOmemSingers(), song.getNOmemComposers(),song.getNOmemWritters(), tempSongID);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                System.out.println(idList);

                try {
                    done =songService.addMemberSingers(tempSongID, idList.get(0),"N");

                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    done = songService.addMemberComposers(tempSongID, idList.get(1),"N");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    done = songService.addMemberWritters(tempSongID, idList.get(2),"N");
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                NotificationService nService = new NotificationService();
                SAdashboardService saService = new SAdashboardService();
                String fullname = null;

                try {
                    fullname = saService.getMemberFULLName(uid);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                if (done){
                    try {
                        done = nService.setNotificationSongRegRequest(ut-1, fullname + " has sent a request for a song ownership change");
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

        else{
            String body = data.getBody(request);
            SongOwnerhip obj;
            Gson gson = new Gson();
            obj = gson.fromJson(body, SongOwnerhip.class);
            System.out.println(obj);

            if (obj.getReqType() == 100){
                ArrayList<String> info = new ArrayList<>();
                ArrayList<ArrayList<String>> singers = new ArrayList<>();
                ArrayList<ArrayList<String>> composers = new ArrayList<>();
                ArrayList<ArrayList<String>> writers = new ArrayList<>();

                ChangeSongOwnershipService songs = new ChangeSongOwnershipService();

                try {
                    info = songs.getSongDetails(obj.getSongID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    singers = songs.getSingers(obj.getSongID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    composers = songs.getComposers(obj.getSongID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    writers = songs.getWriters(obj.getSongID());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

                SongOwnerhip a = new SongOwnerhip();
                a.setPOSTsingers(singers);
                a.setPOSTcomposers(composers);
                a.setPOSTwriters(writers);
                a.setPOSTinfo(info);

                System.out.println(a);
                Gson gson2 = new Gson();
                String saobj = gson2.toJson(a);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(saobj);
            }

        }
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
                idsMem.add(String.valueOf(id));
                fnamesMem.add(k[0]);
                lnamesMem.add(k[1]);
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
                idsMem.add(String.valueOf(id));
                fnamesMem.add(k[0]);
                lnamesMem.add(k[1]);
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
}
