package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PendingSongReqDAOImpl implements PendingSongReqDAO{

    public ArrayList<ArrayList<String>> getPendingSongs(int ut, int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();

        if (ut == 4){
            String q = "SELECT Temp_song_id ,Song_name ,version, published_year, Type_of_request FROM song_requests WHERE member_id = ? AND (status = 0 OR status = 1) AND rejected = 0 AND is_cancelled = 0;";
            PreparedStatement stmt = connection.prepareStatement(q);
            stmt.setInt(1, uid);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ArrayList<String> x = new ArrayList<>();
                x.add(String.valueOf(resultSet.getInt(1)));
                x.add(resultSet.getString(2));
                x.add(String.valueOf(resultSet.getInt(3)));
                x.add(String.valueOf(resultSet.getInt(4)));
                x.add(String.valueOf(resultSet.getInt(5)));

                finalOne.add(x);
            }
        }

        else if(ut == 3){
            String q = "SELECT Temp_song_id ,Song_name ,version, published_year, Type_of_request FROM song_requests WHERE emp_id = ? AND (status = 0 OR status = 1) AND rejected = 0 AND is_cancelled = 0;";
            PreparedStatement stmt = connection.prepareStatement(q);
            stmt.setInt(1, uid);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ArrayList<String> x = new ArrayList<>();
                x.add(String.valueOf(resultSet.getInt(1)));
                x.add(resultSet.getString(2));
                x.add(String.valueOf(resultSet.getInt(3)));
                x.add(String.valueOf(resultSet.getInt(4)));
                x.add(String.valueOf(resultSet.getInt(5)));

                finalOne.add(x);
            }
        }

        return finalOne;
    }


    public ArrayList<String> getAllSongDetails(int tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<String> info = new ArrayList<>();
        Connection connection = DBConnection.getObj().getConnection();

        info.add(String.valueOf(tempSongID));

        String q2 = "SELECT song_name, version, published_year FROM song_requests WHERE temp_song_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q2);
        stmt.setInt(1, tempSongID);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            info.add(resultSet.getString(1));
            info.add(String.valueOf(resultSet.getInt(2)));
            info.add(String.valueOf(resultSet.getInt(3)));
        }

        return info;
    }


    public ArrayList<ArrayList<String>> getAllSingers(int tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<ArrayList<String>> MemIds = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> fname = new ArrayList<>();
        ArrayList<String> lname = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        String q3 = "SELECT Member_id FROM song_requests_singers WHERE temp_song_id = ? ";
        PreparedStatement stmt2 = connection.prepareStatement(q3);
        stmt2.setInt(1, tempSongID);
        ResultSet resultSet2 = stmt2.executeQuery();

        while (resultSet2.next()) {
            temp.add(String.valueOf(resultSet2.getInt(1)));
        }

        MemIds.add(temp);

        String q2 = "SELECT first_name, last_name FROM members WHERE Member_id = ? ;";
        for (String name : temp) {
            PreparedStatement preparedStatement = connection.prepareStatement(q2);
            preparedStatement.setInt(1, Integer.parseInt(name));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                fname.add(rs.getString(1));
                lname.add(rs.getString(2));
            }

        }
        MemIds.add(fname);
        MemIds.add(lname);

        return MemIds;
    }

    public ArrayList<ArrayList<String>> getAllComposers(int tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<ArrayList<String>> MemIds = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> fname = new ArrayList<>();
        ArrayList<String> lname = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        String q3 = "SELECT Member_id FROM song_request_composers WHERE temp_song_id = ? ";
        PreparedStatement stmt2 = connection.prepareStatement(q3);
        stmt2.setInt(1, tempSongID);
        ResultSet resultSet2 = stmt2.executeQuery();

        while (resultSet2.next()) {
            temp.add(String.valueOf(resultSet2.getInt(1)));
        }

        MemIds.add(temp);

        String q2 = "SELECT first_name, last_name FROM members WHERE Member_id = ? ;";
        for (String name : temp) {
            PreparedStatement preparedStatement = connection.prepareStatement(q2);
            preparedStatement.setInt(1, Integer.parseInt(name));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                fname.add(rs.getString(1));
                lname.add(rs.getString(2));
            }

        }
        MemIds.add(fname);
        MemIds.add(lname);

        return MemIds;
    }

    public ArrayList<ArrayList<String>> getAllWriters(int tempSongID) throws SQLException, ClassNotFoundException{
        ArrayList<ArrayList<String>> MemIds = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> fname = new ArrayList<>();
        ArrayList<String> lname = new ArrayList<>();

        Connection connection = DBConnection.getObj().getConnection();

        String q3 = "SELECT Member_id FROM song_request_song_writers WHERE temp_song_id = ? ";
        PreparedStatement stmt2 = connection.prepareStatement(q3);
        stmt2.setInt(1, tempSongID);
        ResultSet resultSet2 = stmt2.executeQuery();

        while (resultSet2.next()) {
            temp.add(String.valueOf(resultSet2.getInt(1)));
        }

        MemIds.add(temp);

        String q2 = "SELECT first_name, last_name FROM members WHERE Member_id = ? ;";
        for (String name : temp) {
            PreparedStatement preparedStatement = connection.prepareStatement(q2);
            preparedStatement.setInt(1, Integer.parseInt(name));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                fname.add(rs.getString(1));
                lname.add(rs.getString(2));
            }

        }
        MemIds.add(fname);
        MemIds.add(lname);

        return MemIds;
    }


    public boolean cancelReq(int tid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE song_requests SET is_cancelled = 1 WHERE Temp_Song_ID = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,tid);

        boolean output = false;
        if (stmt.executeUpdate()>0){
            output = true;
        }

        return output;
    }
}
