package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.UserLoginModel;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO{

    public UserLoginModel  login(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {

//        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT first_name,last_name,email,user_type FROM(SELECT first_name,last_name,email,user_type,password FROM officials WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM members WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM basic_users WHERE email=? AND password=?) AS T WHERE email=? AND password=?;");
//        String q = "SELECT first_name,last_name,email,user_type FROM officials WHERE email=? AND password=?;";

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name,last_name,email,user_type,password FROM officials WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM members WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM basic_users WHERE email=? AND password=?";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());
        stmt.setString(3,userLoginModel.getEmail());
        stmt.setString(4, userLoginModel.getPassword());
        stmt.setString(5,userLoginModel.getEmail());
        stmt.setString(6, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            userLoginModel.setFirstName(resultSet.getString(1));
            userLoginModel.setLastName(resultSet.getString(2));
            userLoginModel.setEmail(resultSet.getString(3));
            userLoginModel.setUserType(resultSet.getInt(4));
            return userLoginModel;
        }
        else {
            return null;
        }
    }


    public int getUserID (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {

//        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT first_name,last_name,email,user_type FROM(SELECT first_name,last_name,email,user_type,password FROM officials WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM members WHERE email=? AND password=? UNION SELECT first_name,last_name,email,user_type,password FROM basic_users WHERE email=? AND password=?) AS T WHERE email=? AND password=?;");
//        String q = "SELECT first_name,last_name,email,user_type FROM officials WHERE email=? AND password=?;";

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Emp_id AS uid FROM officials WHERE email=? AND password=? UNION SELECT member_id AS uid FROM members WHERE email=? AND password=? UNION SELECT user_id AS uid FROM basic_users WHERE email=? AND password=?";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());
        stmt.setString(3,userLoginModel.getEmail());
        stmt.setString(4, userLoginModel.getPassword());
        stmt.setString(5,userLoginModel.getEmail());
        stmt.setString(6, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    public UserLoginModel login2(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name,last_name,email,user_type,password FROM members WHERE email=? AND password=? ";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            userLoginModel.setFirstName(resultSet.getString(1));
            userLoginModel.setLastName(resultSet.getString(2));
            userLoginModel.setEmail(resultSet.getString(3));
            userLoginModel.setUserType(resultSet.getInt(4));
            return userLoginModel;
        }
        else {
            return null;
        }
    }

    public int getUserID2 (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT member_id AS uid FROM members WHERE email=? AND password=? ";
        PreparedStatement stmt = connection.prepareStatement(q);

        String hashPass = doHash( userLoginModel.getPassword());
        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2,hashPass);

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }
    }

    public String doHash(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


}
