package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.UserLoginModel;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Emp_id AS uid FROM officials WHERE email=? AND password=? UNION SELECT member_id AS uid FROM members WHERE email=? AND password=? UNION SELECT user_id AS uid FROM basic_users WHERE email=? AND password=?";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1, userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());
        stmt.setString(3, userLoginModel.getEmail());
        stmt.setString(4, userLoginModel.getPassword());
        stmt.setString(5, userLoginModel.getEmail());
        stmt.setString(6, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
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

    public int emailVerified(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Emp_id AS uid , Verify_email, User_Type FROM officials WHERE email=? AND password=? UNION SELECT member_id AS uid,Verify_email, User_Type FROM members WHERE email=? AND password=? UNION SELECT user_id AS uid,Verify_email, User_Type FROM basic_users WHERE email=? AND password=?";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1, userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());
        stmt.setString(3, userLoginModel.getEmail());
        stmt.setString(4, userLoginModel.getPassword());
        stmt.setString(5, userLoginModel.getEmail());
        stmt.setString(6, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        int isValid = 0;
        int ut = 0;
        int uid = 0;

        SecureRandom rand = new SecureRandom();
        String pin = "" + rand.nextInt(1000000);

        if (resultSet.next()) {
            uid = resultSet.getInt(1);
            isValid = resultSet.getInt(2);
            ut = resultSet.getInt(3);

            if (isValid == 0) {

                if (ut == 5) {
                    String q1 = "UPDATE basic_users SET email_id = ? WHERE user_id = ? ;";
                    stmt = connection.prepareStatement(q1);
                    stmt.setInt(1, Integer.parseInt(pin));
                    stmt.setInt(2, uid);

                    if (stmt.executeUpdate() > 0) {
                        return Integer.parseInt(pin);
                    }
                } else if (ut == 4) {
                    String q1 = "UPDATE members SET email_id = ? WHERE member_id = ? ;";
                    stmt = connection.prepareStatement(q1);
                    stmt.setInt(1, Integer.parseInt(pin));
                    stmt.setInt(2, uid);

                    if (stmt.executeUpdate() > 0) {
                        return Integer.parseInt(pin);
                    }
                } else {
                    String q1 = "UPDATE officials SET email_id = ? WHERE emp_id = ? ;";
                    stmt = connection.prepareStatement(q1);
                    stmt.setInt(1, Integer.parseInt(pin));
                    stmt.setInt(2, uid);

                    if (stmt.executeUpdate() > 0) {
                        return Integer.parseInt(pin);
                    }
                }
            }
            else {
                return -1;
            }
        }
        return -200;
    }


    public ArrayList<Integer> getUserIDusingPin(int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT emp_id AS uid, user_type FROM officials WHERE email_id = ? UNION SELECT member_id AS uid, user_type FROM members WHERE email_id = ? UNION SELECT user_id AS uid, user_type FROM basic_users WHERE email_id = ? ";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,pin);
        stmt.setInt(2, pin);
        stmt.setInt(3, pin);

        ResultSet resultSet = stmt.executeQuery();
        ArrayList<Integer> x = new ArrayList<>();

        if (resultSet.next()) {
            x.add(resultSet.getInt(1));
            x.add(resultSet.getInt(2));
        }

        return x;

    }

    public boolean verifyEmailSO(int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "UPDATE basic_users SET Verify_email = 1 WHERE email_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, pin);

        return stmt.executeUpdate() > 0;
    }

    public boolean verifyEmailMem(int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "UPDATE members SET Verify_email = 1 WHERE email_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, pin);

        return stmt.executeUpdate() > 0;
    }

    public boolean verifyEmailEmp(int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "UPDATE officials SET Verify_email = 1 WHERE email_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, pin);

        return stmt.executeUpdate() > 0;
    }

    public boolean checkTimeForEmail(int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT emp_id AS uid, user_type FROM officials WHERE email_id = ? AND CURRENT_TIMESTAMP - Email_sent_time >= 60  UNION SELECT member_id AS uid, user_type FROM members WHERE email_id=? AND Email_sent_time + 60 >= CURRENT_TIMESTAMP UNION SELECT user_id AS uid, user_type FROM basic_users WHERE email_id=? AND Email_sent_time + 60 >= CURRENT_TIMESTAMP ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, pin);
        stmt.setInt(2, pin);
        stmt.setInt(3, pin);

        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
            int uid = resultSet.getInt(1);
            int ut = resultSet.getInt(2);

            if (ut == 5) {
                String q1 = "UPDATE basic_users SET Email_sent_time = CURRENT_TIMESTAMP WHERE user_id = ? ;";
                stmt = connection.prepareStatement(q1);
                stmt.setInt(1, uid);

                return stmt.executeUpdate() > 0;

            } else if (ut == 4) {
                String q1 = "UPDATE members SET Email_sent_time = CURRENT_TIMESTAMP WHERE member_id = ? ;";
                stmt = connection.prepareStatement(q1);
                stmt.setInt(1, uid);

                return stmt.executeUpdate() > 0;

            } else {
                String q1 = "UPDATE officials SET Email_sent_time = CURRENT_TIMESTAMP WHERE emp_id = ? ;";
                stmt = connection.prepareStatement(q1);
                stmt.setInt(1, uid);

                return stmt.executeUpdate() > 0;
            }
        }

        return true;
    }


    public int setEmailIDForSO(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();

        SecureRandom rand = new SecureRandom();
        String pin = "" + rand.nextInt(1000000);

        String q1 = "UPDATE basic_users SET email_id = ? WHERE user_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);
        stmt.setInt(1, Integer.parseInt(pin));
        stmt.setInt(2, uid);

        if(stmt.executeUpdate() > 0){
            return Integer.parseInt(pin);
        }

        return 0;
    }


    public boolean set2Factor(int uid, int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE officials SET SMS_pin = ? WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, pin);
        stmt.setInt(2, uid);

        return stmt.executeUpdate() > 0;
    }

    public boolean get2Factor(int uid, int pin) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SMS_pin FROM officials WHERE emp_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, uid);

        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()) {
            System.out.println(pin);
            System.out.println(resultSet.getInt(1));
            return pin == resultSet.getInt(1);
        }
        return false;
    }

}
