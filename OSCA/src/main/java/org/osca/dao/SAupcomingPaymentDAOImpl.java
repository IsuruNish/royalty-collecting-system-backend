package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SAupcomingPaymentDAOImpl implements SAupcomingPaymentDAO{

    public ArrayList<ArrayList<String>> getCancelledLicenses() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_id, concert_name, Total_Fee, Cancellation_Fee, concert_id FROM concert WHERE Cancelled = 1 AND  is_paid = 0 ;";
        PreparedStatement stmt = connection.prepareStatement(q);
//        stmt.setInt(1,uid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ArrayList<String>> concertInfo = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();

            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));

            double tot = 0;
            double canFee = 0;

            tot = resultSet.getDouble(3);
            canFee = resultSet.getDouble(4);
            x.add(String.valueOf(tot - canFee));

            x.add(String.valueOf(resultSet.getInt(5)));

            concertInfo.add(x);
        }

        return concertInfo;
    }


    public ArrayList<ArrayList<String>> getMemPayments() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT song_id, concert_id, member_id, income FROM song_income WHERE Cancel_status = 0 AND  is_paid = 0 AND concert_date <= CURRENT_DATE ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ArrayList<String>> concertInfo = new ArrayList<>();

        while(resultSet.next()){
            ArrayList<String> x = new ArrayList<>();
            ArrayList<String> memInfo = new ArrayList<>();
            String sName = null;
            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(String.valueOf(resultSet.getInt(2)));
            x.add(String.valueOf(resultSet.getInt(3)));
            x.add("2");
            x.add(String.valueOf(resultSet.getDouble(4)));

            memInfo = getMemberInfo(resultSet.getInt(3));
            sName = getSongInfo(resultSet.getInt(1));
            x.addAll(memInfo);
            x.add(sName);

            concertInfo.add(x);
        }

        return concertInfo;
    }

    public ArrayList<String> getMemberInfo(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT First_name, last_name, bank_no, bank_name, bank_branch FROM members WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
        }

        return x;
    }

    public String getSongInfo(int id) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT temp_song_id FROM song WHERE song_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,id);
        ResultSet resultSet = stmt.executeQuery();


        int songID = 0;
        if(resultSet.next()){
            songID = resultSet.getInt(1);
        }

        String q2 = "SELECT song_name FROM song_requests WHERE temp_song_id = ? ;";
        PreparedStatement stmt2 = connection.prepareStatement(q2);
        stmt2.setInt(1,songID);
        ResultSet resultSet2 = stmt2.executeQuery();

        String sName = null;

        if(resultSet2.next()){
            sName = resultSet2.getString(1);
        }

        return sName;
    }



    public Boolean setPaymentSO(int uid, int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET is_paid = 1 WHERE concert_id = ? AND user_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,concertID);
        stmt.setInt(2,uid);

        return stmt.executeUpdate() > 0;
    }

    public Boolean setPaymentMem(int uid, int concertID, int songID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_income SET is_paid = 1 WHERE concert_id = ? AND song_id = ? AND member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,concertID);
        stmt.setInt(2,songID);
        stmt.setInt(3,uid);

        return stmt.executeUpdate() > 0;
    }

    public ArrayList<String> getSoDetails(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT NIC, phone_number, email FROM basic_users WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
        }

        return x;
    }

    public ArrayList<String> getMemDetails(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT NIC, phone_number, email, bank_no, bank_name, bank_branch FROM members WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,uid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
        }

        return x;


    }
}
