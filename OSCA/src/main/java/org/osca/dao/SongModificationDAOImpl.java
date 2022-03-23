package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.SongModification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongModificationDAOImpl implements SongModificationDAO{

    public ArrayList<ArrayList<Integer>> getSongIDs() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Temp_song_id, song_id FROM song WHERE delete_status = 0 ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> song = new ArrayList<>();
        ArrayList<ArrayList<Integer>> finalOne = new ArrayList<>();

        while(resultSet.next()){
            temp.add(resultSet.getInt(1));
            song.add(resultSet.getInt(2));
        }

        finalOne.add(temp);
        finalOne.add(song);
        return finalOne;
    }

    public ArrayList<ArrayList<String>> getSongs() throws SQLException, ClassNotFoundException {
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> songids = new ArrayList<>();
        ids = getSongIDs().get(0);
        songids = getSongIDs().get(1);

        Connection connection = DBConnection.getObj().getConnection();
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();

        int i = 0;
        for (Integer id : ids) {
            String q = "SELECT Temp_song_id ,Song_name ,version, published_year FROM song_requests WHERE temp_song_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(q);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                ArrayList<String> x = new ArrayList<>();
                x.add(String.valueOf(resultSet.getInt(1)));
                x.add(String.valueOf(songids.get(i)));
                x.add(resultSet.getString(2));
                x.add(resultSet.getString(3));
                x.add(resultSet.getString(4));

                i++;
                finalOne.add(x);
            }
        }
        return finalOne;
    }

    public boolean sendSongDeleteReq(int ut, SongModification song,  int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        PreparedStatement preparedStatement = null;

        if (ut == 4){
            String query = "INSERT INTO song_requests (Corresponding_id,member_id, song_name , version, published_year, status, rejected, type_of_request,s_flag, date) VALUE(?,?,?,?,?,0,0,3,0,CURRENT_DATE)";
            preparedStatement = connection.prepareStatement(query);
        }

        else if (ut == 3){
            String query = "INSERT INTO song_requests (Corresponding_id,emp_id, song_name , version, published_year, status, rejected, type_of_request,s_flag, date) VALUE(?,?,?,?,?,1,0,3,0,CURRENT_DATE)";
            preparedStatement = connection.prepareStatement(query);
        }

        preparedStatement.setInt(1,song.getTempSongID());
        preparedStatement.setInt(2,uid);
        preparedStatement.setString(3,song.getSongName());
        preparedStatement.setInt(4,song.getVersion());
        preparedStatement.setInt(5,song.getPublishedYear());
        return preparedStatement.executeUpdate() > 0;
    }

}
