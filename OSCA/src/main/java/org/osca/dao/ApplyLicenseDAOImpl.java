package org.osca.dao;

import com.google.common.hash.Hashing;
import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.License;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplyLicenseDAOImpl implements ApplyLicenseDAO{
    public ArrayList<ArrayList<Integer>> getSongIDs() throws SQLException, ClassNotFoundException{
        ArrayList<Integer> tempSongID = new ArrayList<>();
        ArrayList<Integer> songID = new ArrayList<>();
        ArrayList<ArrayList<Integer>> finalOne = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "SELECT temp_song_id,song_id FROM song WHERE delete_status = 0;";
        PreparedStatement stmt = connection.prepareStatement(q1);

        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()) {
            tempSongID.add(resultSet.getInt(1));
            songID.add(resultSet.getInt(2));
        }

        finalOne.add(tempSongID);
        finalOne.add(songID);

        return finalOne;
    }


    public ArrayList<String> getSongNames(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<String> songs = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        for (Integer integer : tempSongID) {
            String q2 = "SELECT song_name FROM song_requests WHERE temp_song_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(q2);
            stmt.setInt(1, integer);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                songs.add(resultSet.getString(1));
            }
        }
        return songs;
    }


    public ArrayList<Integer> getSongYears(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<Integer> year = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        for (Integer integer : tempSongID) {
            String q2 = "SELECT published_year FROM song_requests WHERE temp_song_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(q2);
            stmt.setInt(1, integer);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                year.add(resultSet.getInt(1));
            }
        }
        return year;
    }



    public ArrayList<ArrayList<String>> getSingersFirstName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<ArrayList<String>> singersFname = new ArrayList<>();
        Connection connection = DBConnection.getObj().getConnection();

        for (Integer integer : tempSongID) {
            String q3 = "SELECT M.first_name FROM members M LEFT JOIN song_singers SS ON M.Member_id = SS.member_id WHERE SS.song_ID = ?;";
            PreparedStatement stmt = connection.prepareStatement(q3);
            stmt.setInt(1, integer);
            System.out.println(integer);
            ResultSet resultSet = stmt.executeQuery();

            ArrayList<String> t1= new ArrayList<>();

            while(resultSet.next()) {
                t1.add(resultSet.getString(1));
            }
            singersFname.add(t1);
        }

        System.out.println(singersFname);


        return singersFname;
    }


    public ArrayList<ArrayList<String>> getSingersLastName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<ArrayList<String>> singersLname = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        for (Integer integer : tempSongID) {
            String q3 = "SELECT M.last_name FROM members M LEFT JOIN song_singers SS ON M.Member_id = SS.member_id WHERE SS.song_ID = ?;";
            PreparedStatement stmt = connection.prepareStatement(q3);
            stmt.setInt(1, integer);
            ResultSet resultSet = stmt.executeQuery();

            ArrayList<String> t2= new ArrayList<>();

            while(resultSet.next()) {
                t2.add(resultSet.getString(1));
            }
            singersLname.add(t2);
        }

        System.out.println(singersLname);


        return singersLname;
    }




    //do POST



    public boolean setConcertClose(License license, int uid) throws SQLException, ClassNotFoundException{

//        Connection connection = DBConnection.getObj().getConnection();
//
//        String q1 = "INSERT INTO concert (user_id, concert_name, venue, type, total_fee,Fee_without_commission,Payment_status,Date_Applied,No_of_Songs,Status,Rejected,Commission,Cancelled,) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
//        PreparedStatement preparedStatement = connection.prepareStatement(q1);
//
//
//        preparedStatement.setString(1,user.getNic());
//        preparedStatement.setString(2,user.getFname());
//        preparedStatement.setString(3,user.getLname());
//        preparedStatement.setString(4,user.getPhoneNo());
//        preparedStatement.setString(5,user.getEmail());
//        preparedStatement.setString(6,user.getAccNo());
//        preparedStatement.setString(7,user.getBankName());
//        preparedStatement.setString(8,user.getBankBranch());
//        preparedStatement.setString(9,"M");
//        preparedStatement.setInt(10,0);
//        preparedStatement.setString(11,sha256hex);
//        preparedStatement.setInt(12,4);
//        preparedStatement.setInt(13,uid);
//
//        int a = preparedStatement.executeUpdate();
//
//        if (a>0){
//            try {
//                javaMailUtil.notifyUser(user.getEmail(),""+password, user.getFname());
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//        return false;

        return false;



    }




    public boolean setConcertSongsClose(License license, int uid) throws SQLException, ClassNotFoundException{


        return false;
    }

    public boolean setConcertOpen(License license, int uid) throws SQLException, ClassNotFoundException{
        return false;

    }

}
