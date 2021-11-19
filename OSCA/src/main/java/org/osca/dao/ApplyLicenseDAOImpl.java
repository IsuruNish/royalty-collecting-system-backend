package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.License;
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



    public boolean setConcertClose(License license, int uid, double commison, double totFee, double feeWithNoCommison,int songsNo) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "INSERT INTO concert (user_id, concert_name, concert_date, venue, type, total_fee,Fee_without_commission,Payment_status,No_of_Songs,Status,Rejected,Commission,Cancelled,Date_Applied) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);


        preparedStatement.setInt(1,uid);
        preparedStatement.setString(2,license.getConcertName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(license.getDate()));
        preparedStatement.setString(4,license.getVenue());
        preparedStatement.setString(5,"Close");
        preparedStatement.setDouble(6,totFee);
        preparedStatement.setDouble(7,feeWithNoCommison);
        preparedStatement.setInt(8,0);
        preparedStatement.setInt(9,songsNo);
        preparedStatement.setInt(10,0);
        preparedStatement.setInt(11,0);
        preparedStatement.setDouble(12,commison);
        preparedStatement.setInt(13,0);

        return preparedStatement.executeUpdate() > 0;


    }




    public boolean setConcertSongsClose(License license, int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT concert_id FROM concert WHERE user_id = ? AND concert_name = ? AND venue = ? AND no_of_songs = ? AND status = 0 AND rejected = 0 AND cancelled = 0 AND Date_applied = CURRENT_DATE;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, uid);
        stmt.setString(2, license.getConcertName());
        stmt.setString(3, license.getVenue());
        stmt.setInt(4, license.getSongIds().size());

        ResultSet resultSet = stmt.executeQuery();

        int concertID= 0;
        if(resultSet.next()) {
            concertID = resultSet.getInt(1);
        }

        int added = 0;
        for (int i = 0; i < license.getSongIds().size(); i++){
            String q1 = "INSERT INTO concert_songs (concert_id, song_id, approved) VALUE(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(q1);
            preparedStatement.setInt(1,concertID);
            preparedStatement.setInt(2,license.getSongIds().get(i));
            preparedStatement.setInt(3,0);

            added = preparedStatement.executeUpdate();

        }


        return added > 0;
    }

    public boolean setConcertOpen(License license, int uid, double fee) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "INSERT INTO concert (user_id, concert_name, concert_date, venue, type, total_fee,Fee_without_commission,Payment_status,Status,Rejected,Commission,Cancelled,Date_Applied) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);

        preparedStatement.setInt(1,uid);
        preparedStatement.setString(2,license.getConcertName());
        preparedStatement.setDate(3, java.sql.Date.valueOf(license.getDate()));
        preparedStatement.setString(4,license.getVenue());
        preparedStatement.setString(5,"Open");
        preparedStatement.setDouble(6,fee);
        preparedStatement.setDouble(7,fee);
        preparedStatement.setInt(8,0);
        preparedStatement.setInt(9,0);
        preparedStatement.setInt(10,0);
        preparedStatement.setDouble(11,fee);
        preparedStatement.setInt(12,0);

        return preparedStatement.executeUpdate() > 0;
    }

    public double getLicenseCommision() throws SQLException, ClassNotFoundException{
        double x = 0.0;
        String q1 = null;
        String q2 = null;

        Connection connection = DBConnection.getObj().getConnection();
        q1 = "SELECT prev_value FROM system_details WHERE starting_date > CURRENT_DATE AND key_id = 1; ";

        PreparedStatement stmt = connection.prepareStatement(q1);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        q2 = "SELECT new_value FROM system_details WHERE starting_date <= CURRENT_DATE AND key_id = 1; ";
        stmt = connection.prepareStatement(q2);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        return x;

    }



    public double getTotFee(int id) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "SELECT total_fee FROM concert WHERE concert_id = ?; ";

        PreparedStatement stmt = connection.prepareStatement(q1);

        stmt.setInt(1,id);

        ResultSet resultSet = stmt.executeQuery();

        double x = 0;
        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        return x;
    }


    public int getConcertIDClose(License license, int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT concert_id FROM concert WHERE user_id = ? AND concert_name = ? AND venue = ? AND no_of_songs = ? AND status = 0 AND rejected = 0 AND cancelled = 0 AND Date_applied = CURRENT_DATE;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, uid);
        stmt.setString(2, license.getConcertName());
        stmt.setString(3, license.getVenue());
        stmt.setInt(4, license.getSongIds().size());

        ResultSet resultSet = stmt.executeQuery();

        int concertID = 0;
        if (resultSet.next()) {
            concertID = resultSet.getInt(1);
        }

        return concertID;
    }


    public int getConcertIDOpen(License license, int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT concert_id FROM concert WHERE user_id = ? AND concert_name = ? AND venue = ? AND status = 0 AND rejected = 0 AND cancelled = 0 AND Date_applied = CURRENT_DATE;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, uid);
        stmt.setString(2, license.getConcertName());
        stmt.setString(3, license.getVenue());

        ResultSet resultSet = stmt.executeQuery();

        int concertID = 0;
        if (resultSet.next()) {
            concertID = resultSet.getInt(1);
        }

        return concertID;
    }

    public boolean setSlipPayment(int concertID, String url) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET Payment_slip_link = ? WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,url);
        stmt.setInt(2,concertID);

        return stmt.executeUpdate() > 0;
    }
}
