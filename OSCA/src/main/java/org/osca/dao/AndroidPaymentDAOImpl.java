package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.MemberDashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.osca.dao.SAupcomingPaymentDAOImpl;

public class AndroidPaymentDAOImpl implements AndroidPaymentDAO{
    public ArrayList<ArrayList<String>> getUpcomingPaymentsForAndroid(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "SELECT Concert_id, income, song_id FROM song_income WHERE CURRENT_DATE < concert_date AND Member_ID = ? AND cancel_status = 0 AND delete_flag = 0;";
        PreparedStatement preparedStatement = connection.prepareStatement(q);

        preparedStatement.setInt(1,uid);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        ArrayList<String> strArr = new ArrayList<>();

        SAupcomingPaymentDAOImpl newObjForSong = new SAupcomingPaymentDAOImpl();
        while(resultSet.next()){
            ArrayList<String> temp = new ArrayList<>();

            int NewSongID = resultSet.getInt(3);
            String NewSongName = newObjForSong.getSongInfo(NewSongID);

            String q123 = "SELECT Concert_name FROM concert WHERE concert_ID = ? ;";
            PreparedStatement stmt = connection.prepareStatement(q123);

            stmt.setInt(1,resultSet.getInt(1));
            ResultSet rst = stmt.executeQuery();

            if(rst.next()){
                temp.add(rst.getString(1));
            }
            temp.add(resultSet.getString(2));
            temp.add(NewSongName);
            arr.add(temp);
        }
        return arr;
    }

    public ArrayList<ArrayList<String>> getPastPaymentsForAndroid(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "SELECT Concert_id, income, song_id FROM song_income WHERE CURRENT_DATE >= concert_date AND Member_ID = ? AND cancel_status = 0 AND delete_flag = 0;";
        PreparedStatement preparedStatement = connection.prepareStatement(q);

        preparedStatement.setInt(1,uid);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        ArrayList<String> strArr = new ArrayList<>();

        SAupcomingPaymentDAOImpl newObjForSong = new SAupcomingPaymentDAOImpl();
        while(resultSet.next()){
            ArrayList<String> temp = new ArrayList<>();

            int NewSongID = resultSet.getInt(3);
            String NewSongName = newObjForSong.getSongInfo(NewSongID);

            String q123 = "SELECT Concert_name FROM concert WHERE concert_ID = ? ;";
            PreparedStatement stmt = connection.prepareStatement(q123);

            stmt.setInt(1,resultSet.getInt(1));
            ResultSet rst = stmt.executeQuery();

            if(rst.next()){
                temp.add(rst.getString(1));
            }
            temp.add(resultSet.getString(2));
            temp.add(NewSongName);
            arr.add(temp);
        }

        System.out.println(arr);
        return arr;
    }

}
