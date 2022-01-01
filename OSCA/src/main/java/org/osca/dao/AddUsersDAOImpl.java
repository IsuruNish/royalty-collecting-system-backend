package org.osca.dao;

import com.google.common.hash.Hashing;
import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.AAddUsers;
import org.osca.model.MemberDashboard;

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


        int type = 0;
        if (emp.equals("admin")) {
            type = 2;
        } else {
            type = 3;
        }
        preparedStatement.setString(1, user.getNic());
        preparedStatement.setString(2, user.getFname());
        preparedStatement.setString(3, user.getLname());
        preparedStatement.setString(4, "OSCA Official");
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.setString(6, user.getEmail());
        preparedStatement.setInt(7, 0);
        preparedStatement.setString(8, sha256hex);
        preparedStatement.setInt(9, type);
        preparedStatement.setInt(10, uid);

        int a = preparedStatement.executeUpdate();

        if (a>0) {
            try {
                javaMailUtil.notifyUser(user.getEmail(), "" + password, user.getFname());
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;

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


    public boolean checkMember(MemberDashboard user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "SELECT * FROM members WHERE first_name = ? AND last_name = ? AND email = ? AND nic = ? AND Phone_number = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(q);


        preparedStatement.setString(1,user.getFname());
        preparedStatement.setString(2, user.getLname());
        preparedStatement.setString(3,user.getEmail());
        preparedStatement.setString(4,user.getNic());
        preparedStatement.setString(5,user.getPhoneNo());

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public boolean changeNonMemberToMember(MemberDashboard user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET Member_Active_Status = ? WHERE first_name = ? AND last_name = ? AND email = ? AND nic = ? AND Phone_number = ?;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,"M");
        stmt.setString(2,user.getFname());
        stmt.setString(3,user.getLname());
        stmt.setString(4,user.getEmail());
        stmt.setString(5,user.getNic());
        stmt.setString(6,user.getPhoneNo());

        return stmt.executeUpdate() > 0;
    }



    public boolean addMtoSystem(MemberDashboard user, int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q1 = "INSERT INTO members (NIC, First_Name, Last_Name,Phone_Number, Email, Bank_no, bank_name, bank_branch,Member_Active_Status,Delete_Status, Password, User_Type,Created_By,Created_On) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);

        Mail javaMailUtil=new Mail();
        SecureRandom rand = new SecureRandom();
        String pin=""+rand.nextInt(1000000);
        String password="OSCAinLK@"+pin;
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();


        preparedStatement.setString(1,user.getNic());
        preparedStatement.setString(2,user.getFname());
        preparedStatement.setString(3,user.getLname());
        preparedStatement.setString(4,user.getPhoneNo());
        preparedStatement.setString(5,user.getEmail());
        preparedStatement.setString(6,user.getAccNo());
        preparedStatement.setString(7,user.getBankName());
        preparedStatement.setString(8,user.getBankBranch());
        preparedStatement.setString(9,"M");
        preparedStatement.setInt(10,0);
        preparedStatement.setString(11,sha256hex);
        preparedStatement.setInt(12,4);
        preparedStatement.setInt(13,uid);

        int a = preparedStatement.executeUpdate();

        if (a>0){
            try {
                javaMailUtil.notifyUser(user.getEmail(),""+password, user.getFname());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public int checkMember2(MemberDashboard user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();

        String q = "SELECT member_id FROM members WHERE first_name = ? AND last_name = ? AND Member_Active_Status = 'N';";
        PreparedStatement preparedStatement = connection.prepareStatement(q);


        preparedStatement.setString(1,user.getFname());
        preparedStatement.setString(2, user.getLname());

        ResultSet resultSet = preparedStatement.executeQuery();

        int uid = 0;
        if(resultSet.next()){
            uid = resultSet.getInt(1);
        }
        return uid;
    }

    public boolean changeNonMemberToMember2(MemberDashboard user, int uid, int madeID) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE members SET Member_Active_Status = ? , email = ? , nic = ? , Phone_number = ? , bank_no = ? , bank_name = ? , bank_branch = ? , Password = ? , created_by = ? , created_on = CURRENT_DATE WHERE member_id = ? ;";
//        String q1 = "UPDATE song_composers SET Member_Active_Status = 'M' WHERE member_id = ? ;";
        String q2 = "UPDATE song_requests_singers SET Member_Active_Status = 'M' WHERE member_id = ? ;";
        String q3 = "UPDATE song_request_composers SET Member_Active_Status = 'M' WHERE member_id = ? ;";
        String q4 = "UPDATE song_request_song_writers SET Member_Active_Status = 'M' WHERE member_id = ? ;";
//        String q5 = "UPDATE song_singers SET Member_Active_Status = 'M' WHERE member_id = ? ;";
//        String q6 = "UPDATE song_songwriters SET Member_Active_Status = 'M' WHERE member_id = ? ;";



        PreparedStatement stmt = connection.prepareStatement(q);
//        PreparedStatement stmt1 = connection.prepareStatement(q1);
//        stmt1.setInt(1,uid);
//        boolean a1 = stmt1.executeUpdate()>0;

        PreparedStatement stmt2 = connection.prepareStatement(q2);
        stmt2.setInt(1,uid);
        boolean a2 = stmt2.executeUpdate()>0;

        PreparedStatement stmt3 = connection.prepareStatement(q3);
        stmt3.setInt(1,uid);
        boolean a3 = stmt3.executeUpdate()>0;

        PreparedStatement stmt4 = connection.prepareStatement(q4);
        stmt4.setInt(1,uid);
        boolean a4 = stmt4.executeUpdate()>0;

//        PreparedStatement stmt5 = connection.prepareStatement(q5);
//        stmt5.setInt(1,uid);
//        boolean a5 = stmt5.executeUpdate()>0;
//        PreparedStatement stmt6 = connection.prepareStatement(q6);
//        stmt6.setInt(1,uid);
//        boolean a6 = stmt6.executeUpdate()>0;


        boolean b = a2 || a3 || a4;
        Mail javaMailUtil=new Mail();
        SecureRandom rand = new SecureRandom();
        String pin=""+rand.nextInt(1000000);
        String password="OSCAinLK@"+pin;
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        stmt.setString(1,"M");
        stmt.setString(2,user.getEmail());
        stmt.setString(3,user.getNic());
        stmt.setString(4,user.getPhoneNo());
        stmt.setString(5,user.getAccNo());
        stmt.setString(6,user.getBankName());
        stmt.setString(7,user.getBankBranch());
        stmt.setString(8,sha256hex);
        stmt.setInt(9,madeID);
        stmt.setInt(10,uid);

        boolean a = stmt.executeUpdate() > 0;

        if (a || b){
            try {
                javaMailUtil.notifyUser(user.getEmail(),""+password, user.getFname());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
