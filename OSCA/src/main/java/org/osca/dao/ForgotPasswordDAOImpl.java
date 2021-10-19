package org.osca.dao;

import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.ForgetPassword;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForgotPasswordDAOImpl implements ForgotPasswordDAO {
    public ArrayList<Integer> findEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Emp_ID AS uid, User_type FROM officials WHERE email=?  UNION SELECT Member_ID AS uid, User_type FROM members WHERE email=?  UNION SELECT User_ID AS uid, User_type FROM basic_users WHERE email=? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,fp.getEmail());
        stmt.setString(2,fp.getEmail());
        stmt.setString(3,fp.getEmail());

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(0);
        temp.add(0);
        if(resultSet.next()){
            temp.add(0,resultSet.getInt(1));
            temp.add(1,resultSet.getInt(2));
        }

        return temp;
    }

    public boolean emailTheOTP(ForgetPassword fp, int uid, int ut) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "INSERT INTO forgot_password (OTP, email, verified,uid, user_type, created_date) VALUE(?,?,?,?,?, CURRENT_TIMESTAMP)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);


        Mail javaMailUtil=new Mail();
        SecureRandom rand = new SecureRandom();
        String pin=""+rand.nextInt(1000000);

        preparedStatement.setString(1,pin);
        preparedStatement.setString(2,fp.getEmail());
        preparedStatement.setInt(3,0);
        preparedStatement.setInt(4,uid);
        preparedStatement.setInt(5,ut);

        try {
            javaMailUtil.sendMail(fp.getEmail(),"http://127.0.0.1:5500/landing_page/forgotpwChangePW.html?="+pin);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return preparedStatement.executeUpdate() > 0;
    }



    public boolean findTime(ForgetPassword fp, int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT * FROM forgot_password WHERE email= ? AND CURRENT_TIMESTAMP - created_date <=60 AND verified = 0;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,fp.getEmail());

        ResultSet resultSet = stmt.executeQuery();

        return !resultSet.next();
    }

    public boolean findRecord(ForgetPassword fp) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT * FROM forgot_password WHERE email= ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,fp.getEmail());

        ResultSet resultSet = stmt.executeQuery();

        return resultSet.next();
    }




    public boolean changePass(ForgetPassword fp) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT uid, user_type FROM forgot_password WHERE OTP = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);
        stmt.setInt(1,fp.getOtp());

        ResultSet resultSet = stmt.executeQuery();

        int uid = 0;
        int ut = 0;
        if(resultSet.next()){
            uid = resultSet.getInt(1);
            ut = resultSet.getInt(2);
        }

        String q2 = null;
        if (ut == 4){
            q2 = "UPDATE members SET password = ? WHERE member_id = ? ;";
        }

        else if(ut == 5){
            q2 = "UPDATE basic_users SET password = ? WHERE user_id = ? ;";
        }
        else if(ut == 1 || ut == 2 || ut == 3){
            q2 = "UPDATE officials SET password = ? WHERE emp_id = ? ;";
        }

        stmt = connection.prepareStatement(q2);

        stmt.setString(1,fp.getPass());
        stmt.setInt(2,uid);

        boolean output = false;
        if (stmt.executeUpdate()>0){
            output = true;
        }


        String q3 = "UPDATE forgot_password SET verified = 1 WHERE uid = ? ;";
        stmt = connection.prepareStatement(q3);
        stmt.setInt(1,uid);


        if (stmt.executeUpdate()>0){
            output = true;
        }

        return output;
    }



    public int verify(ForgetPassword fp) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT verified FROM forgot_password WHERE OTP = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,fp.getOtp());
        ResultSet resultSet = stmt.executeQuery();

        int out = 0;
        if(resultSet.next()){
            out = resultSet.getInt(1);
        }

        return out;
    }

}
