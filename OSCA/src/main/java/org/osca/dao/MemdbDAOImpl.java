package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemdbDAOImpl implements MemdbDAO{
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_type,first_name,last_name, email, phone_number FROM members WHERE member_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();
        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
        }


        q = "SELECT income FROM song_income WHERE member_id =? AND status = 2 AND GETDATE()>=concert_date;";

        stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
        }
        else{
            x.add("0");
        }

        q = "SELECT income FROM song_income WHERE member_id = ? AND status = 2 AND GETDATE()<concert_date;";

        stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
        }
        else{
            x.add("0");
        }

        return x;
    }
}
