package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.ChangeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllUsersChangeInfoDAOImpl implements AllUsersChangeInfoDAO{

    public ArrayList<String> getUserDetailsMember(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_type, first_name, last_name, email, nic, phone_number, bank_no, bank_name, bank_branch FROM members WHERE member_id = ? ;";
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
            x.add(resultSet.getString(7));
            x.add(resultSet.getString(8));
            x.add(resultSet.getString(9));
        }
        x.add(String.valueOf(ut));
        return x;
    }

    public ArrayList<String> getUserDetailsSO(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_type, first_name, last_name, email,nic,phone_number FROM basic_users WHERE user_id = ?;";
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

    public boolean updateUserDetailsMember(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET first_name = ?, last_name = ?, nic = ?, phone_number = ?, email = ? WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,user.getFname());
        stmt.setString(2, user.getLname());
        stmt.setString(3,user.getNic());
        stmt.setString(4,user.getPhoneNo());
        stmt.setString(5,user.getEmail());
        stmt.setInt(6,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updateUserDetailsSO(int uid, ChangeInfo user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE basic_users SET first_name = ?, last_name = ?, nic = ?, phone_number = ?, email = ? WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,user.getFname());
        stmt.setString(2, user.getLname());
        stmt.setString(3,user.getNic());
        stmt.setString(4,user.getPhoneNo());
        stmt.setString(5,user.getEmail());
        stmt.setInt(6,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updatePasswordMember(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT password FROM members WHERE member_id = ?;";
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


        String q1 = "UPDATE members SET password = ? WHERE member_id = ? ;";
        stmt = connection.prepareStatement(q1);

        stmt.setString(1,newPass);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updatePasswordSO(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT password FROM basic_users WHERE user_id = ?;";
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


        String q1 = "UPDATE basic_users SET password = ? WHERE user_id = ? ;";
        stmt = connection.prepareStatement(q1);

        stmt.setString(1,newPass);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updateProflePicMember(int uid, String url) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET profile_pic = ? WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,url);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updateProflePicSO(int uid, String url) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE basic_users SET profile_pic = ? WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,url);
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;

    }

    public boolean daleteImageMember(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET profile_pic = ? WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,"https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg");
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean daleteImageSO(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE basic_users SET profile_pic = ? WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,"https://res.cloudinary.com/osca-lk/image/upload/v1633546048/0_byxn7o.jpg");
        stmt.setInt(2,uid);


        return stmt.executeUpdate() > 0;
    }

    public boolean updateBank(int uid, String accNo, String bankName, String bankBranch) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET bank_no = ?, bank_Name = ?, bank_branch = ? WHERE member_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,accNo);
        stmt.setString(2,bankName);
        stmt.setString(3,bankBranch);
        stmt.setInt(4,uid);


        return stmt.executeUpdate() > 0;
    }




}
