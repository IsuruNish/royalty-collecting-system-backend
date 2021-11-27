package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAOImpl implements NotificationDAO{

    public ArrayList<ArrayList<String>> getAllNotificationsFromUID(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT notification_id, message, create_date, create_time, isRead FROM notification WHERE user_id = ? AND delete_flag = 0 ORDER BY create_date DESC , create_time DESC ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, uid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> messages = new ArrayList<>();
        ArrayList<String> nIDs = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> isRead = new ArrayList<>();
        ArrayList<ArrayList<String>> finalLOne = new ArrayList<>();

        while (resultSet.next()) {
            nIDs.add(String.valueOf(resultSet.getInt(1)));
            messages.add(resultSet.getString(2));
            date.add(resultSet.getString(3));
            time.add(resultSet.getString(4));
            isRead.add(String.valueOf(resultSet.getInt(5)));
        }

        finalLOne.add(nIDs);
        finalLOne.add(messages);
        finalLOne.add(date);
        finalLOne.add(time);
        finalLOne.add(isRead);

        return finalLOne;
    }

//    public ArrayList<ArrayList<String>> getAllNotificationsFromUtype(int utype) throws SQLException, ClassNotFoundException{
//        Connection connection = DBConnection.getObj().getConnection();
//        String q = "SELECT notification_id, message, create_date, create_time,isRead FROM notification WHERE user_type = ? AND delete_flag = 0 AND forEmp = 1 ORDER BY create_date DESC , create_time DESC;";
//        PreparedStatement stmt = connection.prepareStatement(q);
//
//        stmt.setInt(1, utype);
//        ResultSet resultSet = stmt.executeQuery();
//
//        ArrayList<String> messages = new ArrayList<>();
//        ArrayList<String> nIDs = new ArrayList<>();
//        ArrayList<String> time = new ArrayList<>();
//        ArrayList<String> date = new ArrayList<>();
//        ArrayList<String> isRead = new ArrayList<>();
//        ArrayList<ArrayList<String>> finalLOne = new ArrayList<>();
//
//        while (resultSet.next()) {
//            nIDs.add(String.valueOf(resultSet.getInt(1)));
//            messages.add(resultSet.getString(2));
//            date.add(resultSet.getString(3));
//            time.add(resultSet.getString(4));
//            isRead.add(String.valueOf(resultSet.getInt(5)));
//        }
//
//        finalLOne.add(nIDs);
//        finalLOne.add(messages);
//        finalLOne.add(date);
//        finalLOne.add(time);
//        finalLOne.add(isRead);
//
//        return finalLOne;
//    }




    public boolean setNotificationForLicenseRequest(String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id, forEmp, create_date ,create_time) VALUE(?,3,1,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);

        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForLicenseAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = null;
        PreparedStatement preparedStatement = null;

        if (stage == 1){
            query = "INSERT INTO notification (message,user_id,forEmp,create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }
        else if (stage == 2){
            query = "INSERT INTO notification (message,user_id,forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }

        assert preparedStatement != null;
        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForLicenseDenied(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id,forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }


    public boolean setNotificationForSongRegRequest(int utype, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id,forEmp, create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,utype);

        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForSongRegAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = null;
        PreparedStatement preparedStatement = null;

        if (stage == 1){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }
        else if (stage == 2){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }
        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForSongRegDenied(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }





//////////////////////////////////////

    public boolean setNotificationForSongOwnRequest(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id,forEmp,create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForSongOwnAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = null;
        PreparedStatement preparedStatement = null;

        if (stage == 1){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }
        else if (stage == 2){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }

        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForSongOwnDenied(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }





    public boolean setNotificationForSongDelRequest(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id, forEmp, create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean setNotificationForSongDelAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = null;
        PreparedStatement preparedStatement = null;

        if (stage == 1){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,1,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }
        else if (stage == 2){
            query = "INSERT INTO notification (message,user_id, forEmp,create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,msg);
            preparedStatement.setInt(2,uid);
        }

        return preparedStatement.executeUpdate() > 0;
    }
    public boolean setNotificationForSongDelDenied(int uid, String msg) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO notification (message,user_id, forEmp, create_date ,create_time) VALUE(?,?,0,CURRENT_DATE ,CURRENT_TIME)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,msg);
        preparedStatement.setInt(2,uid);

        return preparedStatement.executeUpdate() > 0;
    }


    public Boolean deleteNotificationByID(int nid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE notification SET delete_flag = 1 WHERE notification_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,nid);

        return stmt.executeUpdate() > 0;
    }

    public Boolean readAllNotifications(ArrayList<String > ids) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        boolean done = false;
        for (String id : ids) {
            String q = "UPDATE notification SET isRead = 1 WHERE notification_id = ? ;";
            PreparedStatement stmt = connection.prepareStatement(q);
            stmt.setInt(1, Integer.parseInt(id));

            done = stmt.executeUpdate() > 0;

            if (!done){
                return false;
            }
        }
        return true;
    }

    public int newNotificationNumberUID(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT COUNT(*) FROM notification WHERE user_id = ? AND delete_flag = 0 AND isRead = 0 ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, uid);
        ResultSet resultSet = stmt.executeQuery();

        int x = -1;
        while (resultSet.next()) {
            x = resultSet.getInt(1);
        }
        return x;
    }

//    public int newNotificationNumberUTYPE(int utype) throws SQLException, ClassNotFoundException{
//        Connection connection = DBConnection.getObj().getConnection();
//        String q = "SELECT COUNT(*) FROM notification WHERE user_type = ? AND delete_flag = 0 AND isRead = 0 AND forEmp = 1;";
//        PreparedStatement stmt = connection.prepareStatement(q);
//
//        stmt.setInt(1, utype);
//        ResultSet resultSet = stmt.executeQuery();
//
//        int x = -1;
//        while (resultSet.next()) {
//            x = resultSet.getInt(1);
//        }
//        return x;
//    }



}
