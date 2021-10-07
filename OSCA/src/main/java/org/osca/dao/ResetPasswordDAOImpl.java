//package org.osca.dao;
//
//import org.osca.database.DBConnection;
//import org.osca.model.ForgetPassword;
//import org.osca.model.ResetPassword;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ResetPasswordDAOImpl implements ResetPasswordDAO{
//
////    public boolean findEmail(ResetPassword rest) throws SQLException, ClassNotFoundException {
////        System.out.println(rest);
////        String oin = rest.getEmail();
////        Connection connection = DBConnection.getObj().getConnection();
////        String q = "SELECT Emp_ID FROM officials WHERE email=?  UNION SELECT Member_ID FROM members WHERE email=?  UNION SELECT User_ID FROM basic_users WHERE email=? ;";
////        PreparedStatement stmt = connection.prepareStatement(q);
////
////        stmt.setString(1, fp.getEmail());
////        stmt.setString(2, fp.getEmail());
////        stmt.setString(3, fp.getEmail());
////
////
////        ResultSet resultSet = stmt.executeQuery();
////        String s = null;
////        if (resultSet.next()) {
////            s = resultSet.getString(1);
////            System.out.println(s);
////
////
////        }
////        return true;
////    }
//
//    @Override
//    public boolean findPin(ResetPassword reset) throws SQLException, ClassNotFoundException {
//
////        ForgetPassword fp=new ForgetPassword();
////        String email=fp.getEmail();
//
//        System.out.println(reset);
//        return true;
//    }
//}
