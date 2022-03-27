package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpcomingEventsDAOImpl implements UpcomingEventsDAO{

    public ArrayList<ArrayList<String>> getSOUpcomingEvents(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied FROM concert WHERE user_id =? AND status =2 AND rejected = 0 AND cancelled = 0 AND Payment_status = 1 AND CURRENT_DATE <=concert_date ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
            x.add(resultSet.getString(7));

            finaOne.add(x);
        }

        return finaOne;
    }

    public ArrayList<ArrayList<String>> getMemUpcomingEvents(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, income, song_id FROM song_income WHERE member_id =? AND concert_date >= CURRENT_DATE AND cancel_status = 0 AND delete_flag = 0;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();
        int x = 0;
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> concertInfo = new ArrayList<>();
            String songName = null;

            x = resultSet.getInt(1);
            concertInfo = getConcertInfo(x);
            songName = getSongName(resultSet.getInt(3));

            concertInfo.add(String.valueOf(resultSet.getDouble(2)));
            concertInfo.add(songName);

            System.out.println(concertInfo);
            finaOne.add(concertInfo);
        }
        return finaOne;
    }

    public ArrayList<String> getConcertInfo(int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date FROM concert WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,concertID);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
        }

        return x;
    }

    public String getSongName(int songID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT temp_song_id FROM song WHERE song_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,songID);

        ResultSet resultSet = stmt.executeQuery();
        int x = 0;

        if(resultSet.next()){
            x = resultSet.getInt(1);
        }

        String q1 = "SELECT song_name FROM song_requests WHERE temp_song_id = ? ;";
        PreparedStatement stmt2 = connection.prepareStatement(q1);
        stmt2.setInt(1,x);

        ResultSet resultSet2 = stmt2.executeQuery();
        String nameSong = null;

        if(resultSet2.next()){
            nameSong = resultSet2.getString(1);
        }

        return nameSong;
    }

    public ArrayList<ArrayList<String>> getEmpUpcomingEvents() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied, user_id,Commission FROM concert WHERE status = 2 AND rejected = 0 AND cancelled = 0 AND payment_status = 1 AND CURRENT_DATE < concert_date ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        ResultSet resultSet = stmt.executeQuery();
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));

            double fee = 0;
            if (!resultSet.getString(5).equals("Open")){
                fee = resultSet.getDouble(6) + resultSet.getDouble(9);
            }
            else{
                fee = resultSet.getDouble(9);
            }

            x.add(String.valueOf(fee));
            x.add(resultSet.getString(7));
            x.add(getName(resultSet.getInt(8)));
            finaOne.add(x);
        }
        return finaOne;
    }

    public String getName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name,last_name FROM basic_users WHERE user_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);
        ResultSet resultSet = stmt.executeQuery();

        String fname = null;
        if(resultSet.next()){
            fname = resultSet.getString(1) + " "+ resultSet.getString(2) ;
        }
        return fname;
    }



    public ArrayList<ArrayList<String>> getAllSOUpcomingEvents(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied, status FROM concert WHERE user_id =? AND rejected = 0 AND cancelled = 0;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
            x.add(resultSet.getString(7));
            x.add(resultSet.getString(8));

            finaOne.add(x);
        }

        return finaOne;
    }


    public ArrayList<String> getSongs(int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT song_id FROM concert_songs WHERE concert_id =?;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,concertID);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> x = new ArrayList<>();

        while(resultSet.next()){
            x.add(resultSet.getInt(1));
        }

        ArrayList<Integer> tempIDs = new ArrayList<>();
        for (Integer element: x){
            String q2 = "SELECT temp_song_id FROM song WHERE song_id =?;";
            PreparedStatement stmt2 = connection.prepareStatement(q2);
            stmt2.setInt(1,element);
            ResultSet resultSet2 = stmt2.executeQuery();

            if(resultSet2.next()){
                tempIDs.add(resultSet2.getInt(1));
            }
        }

        ArrayList<String> songNames = new ArrayList<>();
        for (Integer element: tempIDs){
            String q3 = "SELECT song_name FROM song_requests WHERE temp_song_id =?;";
            PreparedStatement stmt3 = connection.prepareStatement(q3);
            stmt3.setInt(1,element);
            ResultSet resultSet3 = stmt3.executeQuery();

            if(resultSet3.next()){
                songNames.add(resultSet3.getString(1));
            }
        }


        return songNames;
    }


    public ArrayList<ArrayList<String>>  pendingPaymentsForSO(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied, status FROM concert WHERE user_id =? AND rejected = 0 AND cancelled = 0 AND status = 2 AND CURRENT_DATE < concert_date AND payment_status = 0 AND Payment_slip_link IS null;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
            x.add(resultSet.getString(7));
            x.add(resultSet.getString(8));

            finaOne.add(x);
        }

        return finaOne;
    }


    public ArrayList<ArrayList<String>> getSOPastEvents(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied FROM concert WHERE user_id =? AND status =2 AND rejected = 0 AND cancelled = 0 AND Payment_status = 1 AND concert_date < CURRENT_DATE ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<ArrayList<String>> finaOne = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
            x.add(resultSet.getString(7));
            x.add("Finished");

            finaOne.add(x);
        }


        String q0 = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied FROM concert WHERE user_id =? AND rejected = 1 AND cancelled = 0 ;";
        PreparedStatement stmt0 = connection.prepareStatement(q0);
        stmt0.setInt(1,uid);

        ResultSet resultSet0 = stmt0.executeQuery();

        while(resultSet0.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet0.getInt(1)));
            x.add(resultSet0.getString(2));
            x.add(resultSet0.getString(3));
            x.add(resultSet0.getString(4));
            x.add(resultSet0.getString(5));
            x.add(resultSet0.getString(6));
            x.add(resultSet0.getString(7));
            x.add("Rejected");

            finaOne.add(x);
        }

        String q1 = "SELECT concert_id, concert_name,venue, concert_date, type, total_fee,Date_Applied FROM concert WHERE user_id =? AND cancelled = 1 ;";
        PreparedStatement stmt1 = connection.prepareStatement(q1);
        stmt1.setInt(1,uid);

        ResultSet resultSet1 = stmt1.executeQuery();

        while(resultSet1.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet1.getInt(1)));
            x.add(resultSet1.getString(2));
            x.add(resultSet1.getString(3));
            x.add(resultSet1.getString(4));
            x.add(resultSet1.getString(5));
            x.add(resultSet1.getString(6));
            x.add(resultSet1.getString(7));
            x.add("Cancelled");

            finaOne.add(x);
        }

        return finaOne;
    }

}
