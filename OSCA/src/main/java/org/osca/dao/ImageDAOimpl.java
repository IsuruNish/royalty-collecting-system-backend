package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAOimpl implements ImageDAO{
    public String getEmpPhotofilePic(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT profile_pic FROM officials WHERE emp_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }

        if (x == null){
            return "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
        }
        else{
            return x;
        }
    }



    public String getMemPhotofilePic(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT profile_pic FROM members WHERE member_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }

        if (x == null){
            return "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
        }
        else{
            return x;
        }
    }

    public String getUserPhotofilePic(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT profile_pic FROM basic_users WHERE user_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }

        if (x == null){
            return "https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg";
        }
        else{
            return x;
        }
    }

}
