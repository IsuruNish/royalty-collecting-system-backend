package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.SuperAdminDashboard;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SAchangeinfoDAOimpl implements SAchangeinfoDAO{

    public ArrayList<String> getUserDetails(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_type, first_name, last_name, email,nic,phone_number FROM officials WHERE emp_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();
        int ut = 0;
        if(resultSet.next()){
            ut = resultSet.getInt(1);
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
            x.add(resultSet.getString(6));
        }
        x.add(String.valueOf(ut));
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

        stmt.setString(1,"https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg");
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }


    public boolean updatePassword(int uid,  String oldPass, String newPass) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT password FROM officials WHERE emp_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()) {
            x = resultSet.getString(1);
        }
        else{
            return false;
        }

        if (!x.equals(oldPass)){
            return false;
        }


        String q1 = "UPDATE officials SET password = ? WHERE emp_id = ? ;";
        stmt = connection.prepareStatement(q1);

        stmt.setString(1,newPass);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }



    public boolean setEmailVerificationForEmp(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();

        SecureRandom rand = new SecureRandom();
        String pin = "" + rand.nextInt(1000000);

        String q1 = "UPDATE officials SET email_id = ?, Verify_email = 0 WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, Integer.parseInt(pin));
        stmt.setInt(2, uid);

        return stmt.executeUpdate() > 0;
    }

    public boolean setEmailVerificationForSO(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();

        SecureRandom rand = new SecureRandom();
        String pin = "" + rand.nextInt(1000000);

        String q1 = "UPDATE basic_users SET email_id = ? , Verify_email = 0 WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, Integer.parseInt(pin));
        stmt.setInt(2, uid);

        return stmt.executeUpdate() > 0;
    }

    public boolean setEmailVerificationForMem(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();

        SecureRandom rand = new SecureRandom();
        String pin = "" + rand.nextInt(1000000);

        String q1 = "UPDATE members SET email_id = ? , Verify_email = 0 WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, Integer.parseInt(pin));
        stmt.setInt(2, uid);

        return stmt.executeUpdate() > 0;
    }

    public ArrayList<String> getAllEmails(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT email FROM officials WHERE NOT emp_id = ? UNION SELECT email FROM members WHERE NOT member_id = ? UNION SELECT email FROM basic_users WHERE NOT user_id = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);
        stmt.setInt(2,uid);
        stmt.setInt(3,uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();
        while(resultSet.next()){
            x.add(resultSet.getString(1));
        }
        return x;
    }


}
