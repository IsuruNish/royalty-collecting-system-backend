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



    public ArrayList<ArrayList<String>> getSongIDandSongNames(int concertID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT song_id FROM concert_songs WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1, concertID);
        ResultSet resultSet = stmt.executeQuery();
        ArrayList<Integer> IDs = new ArrayList<>();

        while (resultSet.next()) {
            IDs.add(resultSet.getInt(1));
        }


        ArrayList<String> TempIDs = new ArrayList<>();

        for (Integer id: IDs){
            String q2 = "SELECT temp_song_id FROM song WHERE song_id = ? ;";
            PreparedStatement stmt2 = connection.prepareStatement(q2);
            stmt2.setInt(1, id);
            ResultSet resultSet2 = stmt2.executeQuery();

            if (resultSet2.next()) {
                TempIDs.add(String.valueOf(resultSet2.getInt(1)));
            }
        }



        ArrayList<String> songNames = new ArrayList<>();

        for (String id: TempIDs){
            String q3 = "SELECT song_name FROM song_requests WHERE temp_song_id = ? ;";
            PreparedStatement stmt3 = connection.prepareStatement(q3);
            stmt3.setInt(1, Integer.parseInt(id));
            ResultSet resultSet3 = stmt3.executeQuery();

            if (resultSet3.next()) {
                songNames.add(resultSet3.getString(1));
            }
        }

        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        finalOne.add(TempIDs);
        finalOne.add(songNames);

        return finalOne;
    }

    public ArrayList<Integer> sendNotificationsForRelaventMembers(int tempSongIDs) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        ArrayList<Integer> memberIds = new ArrayList<>();

        String q3 = "SELECT member_id FROM song_request_composers WHERE temp_song_id = ? AND Member_Active_Status = 'M' ;";
        PreparedStatement stmt3 = connection.prepareStatement(q3);
        stmt3.setInt(1, tempSongIDs);
        ResultSet resultSet3 = stmt3.executeQuery();

        while (resultSet3.next()) {
            memberIds.add(resultSet3.getInt(1));
        }

        String q4 = "SELECT member_id FROM song_request_song_writers WHERE temp_song_id = ? AND Member_Active_Status = 'M' ;";
        PreparedStatement stmt4 = connection.prepareStatement(q4);
        stmt4.setInt(1, tempSongIDs);
        ResultSet resultSet4 = stmt4.executeQuery();

        while (resultSet4.next()) {
            memberIds.add(resultSet4.getInt(1));
        }

        return memberIds;
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
