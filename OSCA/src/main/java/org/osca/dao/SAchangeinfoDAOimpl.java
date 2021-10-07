package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.SuperAdminDashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SAchangeinfoDAOimpl implements SAchangeinfoDAO{

    public ArrayList<String> getUserDetails(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name, last_name, email,nic,phone_number FROM officials WHERE emp_id = ?;";
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
        }

        return x;
    }

    public boolean updateUserDetails(int uid, SuperAdminDashboard user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE officials SET first_name = ?, last_name = ?, nic = ?, phone_number = ?, email = ? WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,user.getFname());
        stmt.setString(2, user.getLname());
        stmt.setString(3,user.getNic());
        stmt.setString(4,user.getPhoneNo());
        stmt.setString(5,user.getEmail());
        stmt.setInt(6,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updateProflePic(int uid, String url) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE officials SET profile_pic = ? WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,url);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean daleteImage(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE officials SET profile_pic = ? WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,null);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

}
