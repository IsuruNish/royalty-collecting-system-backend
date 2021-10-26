package org.osca.dao;

import com.google.common.hash.Hashing;
import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.Songs;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRegistrationDAOImpl implements SongRegistrationDAO{
    public ArrayList<ArrayList<String>> getMembers() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Member_ID,first_name,last_name FROM members WHERE Member_Active_Status = 'M';";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<ArrayList<String>> tot = new ArrayList<>();

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
            names.add(resultSet.getString(2)+" "+resultSet.getString(3));
        }

        tot.add(names);
        tot.add(ids);
        return tot;
    }


    public Boolean storeSong(int uid, int ut, Songs song, String link) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = null;


        if(ut == 4){
            q = "INSERT INTO song_requests (member_id,song_Name,version, published_year, status,s_flag,Documentation_link,date) VALUE(?,?,?,?,?,?,?,CURRENT_DATE );";
        }
        else if(ut == 3){
            q = "INSERT INTO song_requests (emp_id,song_Name,version, published_year, status,s_flag,Documentation_link,date) VALUE(?,?,?,?,?,?,?,CURRENT_DATE );";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(q);

        List<String> details = song.getInfo();
        preparedStatement.setInt(1,uid);
        preparedStatement.setString(2,details.get(0));
        preparedStatement.setString(3,details.get(1));
        preparedStatement.setString(4,details.get(2));
        preparedStatement.setInt(5,0);
        preparedStatement.setInt(6,0);
        preparedStatement.setString(7,link);

        return preparedStatement.executeUpdate()>0;
    }



    public int getID(String link) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Temp_song_id FROM song_requests WHERE Documentation_link = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setString(1,link);
        ResultSet resultSet = stmt.executeQuery();

        int id = 0;
        if (resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;
    }

    public int getIDmem(String fname, String lname)throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT member_id FROM members WHERE first_name = ? AND last_name = ? AND Member_Active_Status = 'N';";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,fname);
        stmt.setString(2,lname);

        ResultSet resultSet = stmt.executeQuery();

        int id = 0;
        stmt.setString(1,fname);
        stmt.setString(2,lname);

        if (resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;
    }




    public boolean addMemSingers(int songID, List<String> names,String status)throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();

        String q = "INSERT INTO song_requests_singers (Temp_song_id, member_id,Member_Active_Status) VALUE(?,?,?);";

        int done = 1;
        int checked = 1;
        for (String name : names) {
            System.out.println(name);
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setInt(1, songID);
            preparedStatement.setInt(2, Integer.parseInt(name));
            preparedStatement.setString(3, status);

            done = preparedStatement.executeUpdate();
            if (done * checked == 0) {
                return false;
            }
        }
        return true;
    }


    public boolean addMemComposers(int songID, List<String> names,String status)throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "INSERT INTO song_request_composers (Temp_song_id, member_id,Member_Active_Status) VALUE(?,?,?);";

        int done = 1;
        int checked = 1;
        for (String name : names) {
            System.out.println(name);
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setInt(1, songID);
            preparedStatement.setInt(2, Integer.parseInt(name));
            preparedStatement.setString(3, status);

            done = preparedStatement.executeUpdate();
            if (done * checked == 0) {
                return false;
            }
        }
        return true;
    }
    public boolean addMemWritters(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "INSERT INTO song_request_song_writers (Temp_song_id, member_id, Member_Active_Status) VALUE(?,?,?);";

        int done = 1;
        int checked = 1;
        for (String name : names) {
            System.out.println(name);
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setInt(1, songID);
            preparedStatement.setInt(2, Integer.parseInt(name));
            preparedStatement.setString(3, status);

            done = preparedStatement.executeUpdate();
            if (done * checked == 0) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<String>> checkNoneMembers()throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT member_id,First_name, last_name FROM members WHERE Member_active_status = 'N';";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> fnames = new ArrayList<>();
        ArrayList<String> lnames = new ArrayList<>();
        while (resultSet.next()){
            ids.add(resultSet.getString(1));
            fnames.add(resultSet.getString(2));
            lnames.add(resultSet.getString(3));
        }

        ArrayList<ArrayList<String>> tot = new ArrayList<>();
        tot.add(ids);
        tot.add(fnames);
        tot.add(lnames);

        return tot;
    }

    public boolean addNoneMemToSystem(String fname, String lname)throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "INSERT INTO members (First_name, last_name, Member_Active_Status, Delete_status, user_type) VALUE(?,?,?,?,4);";
        PreparedStatement preparedStatement = connection.prepareStatement(q);


        preparedStatement.setString(1, fname);
        preparedStatement.setString(2, lname);
        preparedStatement.setString(3, "N");
        preparedStatement.setInt(4, 0);


        return preparedStatement.executeUpdate()>0;
    }
}