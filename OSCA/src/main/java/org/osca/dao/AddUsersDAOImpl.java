package org.osca.dao;

import com.google.common.hash.Hashing;
import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.AAddUsers;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUsersDAOImpl implements AddUsersDAO {

    public boolean addEmptoSystem(AAddUsers user, int uid, String emp) throws SQLException, ClassNotFoundException{

        Connection connection = DBConnection.getObj().getConnection();

        String q1 = "INSERT INTO officials (NIC, First_Name, Last_Name,Designation, Phone_Number, Email, Delete_Status,Password, User_Type,Created_By,Created_On) VALUE(?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);

        Mail javaMailUtil=new Mail();
        SecureRandom rand = new SecureRandom();
        String pin=""+rand.nextInt(1000000);
        String password="OSCAinLK@"+pin;
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
//        try {
//            MessageDigest digest=MessageDigest.getInstance("SHA-256");
//            byte[] hashedPassword= digest.digest(password.getBytes(StandardCharsets.UTF_8));
//            String hashed=hashedPassword.toString();
//            preparedStatement.setString(8,hashed);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        try {
            javaMailUtil.notifyUser(user.getEmail(),""+password);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        int type = 0;
        if(emp == "admin"){
            type = 2;
        }
        else{
            type =3;
        }
        preparedStatement.setString(1,user.getNic());
        preparedStatement.setString(2,user.getFname());
        preparedStatement.setString(3,user.getLname());
        preparedStatement.setString(4,"OSCA Official");
        preparedStatement.setString(5,user.getPhone());
        preparedStatement.setString(6,user.getEmail());
        preparedStatement.setInt(7,0);
        preparedStatement.setString(8,sha256hex);
        preparedStatement.setInt(9,type);
        preparedStatement.setInt(10,uid);

        return preparedStatement.executeUpdate() > 0;

    }

    public boolean checkEmail(AAddUsers user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "SELECT email AS email FROM officials WHERE email=? UNION SELECT email AS email FROM members WHERE email=? UNION SELECT email AS email FROM basic_users WHERE email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);


        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3,user.getEmail());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return false;
        }

        return true;
    }


    public boolean addMtoSystem(AAddUsers user,int uid) throws SQLException, ClassNotFoundException{
//        Connection connection = DBConnection.getObj().getConnection();
//
//        String q1 = "INSERT INTO members (NIC, First_Name, Last_Name,Designation, Phone_Number, Email, Delete_Status,Password, User_Type,Created_By,Created_On) VALUE(?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
//        PreparedStatement preparedStatement = connection.prepareStatement(q1);
//
//        Mail javaMailUtil=new Mail();
//        SecureRandom rand = new SecureRandom();
//        String pin=""+rand.nextInt(1000000);
//        String password="OSCAinLK@"+pin;
//        String sha256hex = Hashing.sha256()
//                .hashString(password, StandardCharsets.UTF_8)
//                .toString();
//
//        try {
//            javaMailUtil.notifyUser(user.getEmail(),""+password);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        preparedStatement.setString(1,user.getNic());
//        preparedStatement.setString(2,user.getFname());
//        preparedStatement.setString(3,user.getLname());
//        preparedStatement.setString(4,"OSCA Official");
//        preparedStatement.setString(5,user.getPhone());
//        preparedStatement.setString(6,user.getEmail());
//        preparedStatement.setInt(7,0);
//        preparedStatement.setString(8,sha256hex);
//        preparedStatement.setInt(9,4);
//        preparedStatement.setInt(10,uid);
//
//        return preparedStatement.executeUpdate() > 0;


        return false;
    }

}
