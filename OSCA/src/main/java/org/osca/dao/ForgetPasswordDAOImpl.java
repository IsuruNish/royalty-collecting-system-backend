package org.osca.dao;

import org.osca.controller.ResetPasswordServlet;
import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.model.*;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordDAOImpl implements ForgetPasswordDAO,ResetPasswordDAO{

    public boolean findEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        System.out.println(fp);
        String email=fp.getEmail();
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Emp_ID AS uid FROM officials WHERE email=?  UNION SELECT Member_ID AS uid FROM members WHERE email=?  UNION SELECT User_ID uid FROM basic_users WHERE email=? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,fp.getEmail());
        stmt.setString(2,fp.getEmail());
        stmt.setString(3,fp.getEmail());



        ResultSet resultSet = stmt.executeQuery();
           String s=null;
        if (resultSet.next()) {
            s=resultSet.getString(1);
            System.out.println(s);

            StoreEmail m=new StoreEmail();
            m.setEmail(email);
//              x=Boolean.valueOf(resultSet.getString(1));
//            userLoginModel.setFirstName(resultSet.getString(1));
//            userLoginModel.setLastName(resultSet.getString(2));
//            userLoginModel.setEmail(resultSet.getString(3));
//            userLoginModel.setUserType(resultSet.getInt(4));
//            return userLoginModel;
        }
        if(s!=null)
        {
            Mail javaMailUtil=new Mail();
            SecureRandom rand = new SecureRandom();
            String pin=""+rand.nextInt(10000);
            StorePin p=new StorePin();
            p.setPin(pin);

            try {
                javaMailUtil.sendMail(email,""+pin);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return true;
        }
//       else {
//            Mail javaMailUtil=new Mail();
//            try {
//                javaMailUtil.sendMail(email,"Your email is not valid");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }

          return false;

    }


    @Override
    public boolean findPin(ResetPassword reset) throws SQLException, ClassNotFoundException {

        StorePin p=new StorePin();
        System.out.println(p.getPin());

        System.out.println(reset.getPin());

        StoreEmail m=new StoreEmail();
        String email=m.getEmail();
        System.out.println(email);

        if(reset.getPin().equals(p.getPin())){

            System.out.println("ok");
            Connection connection = DBConnection.getObj().getConnection();
            String q = "SELECT User_Type FROM officials WHERE email=?  UNION SELECT User_Type FROM members WHERE email=?  UNION SELECT User_Type FROM basic_users WHERE email=? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            int s=-1;
            if(resultSet.next()){
                s=resultSet.getInt(1);
            }

            System.out.println(s);

            if(s==1||s==2||s==3){

                String q1 = "UPDATE officials SET Password=? WHERE Email=?;";
                preparedStatement = connection.prepareStatement(q1);
                preparedStatement.setString(1,reset.getPass1());
                preparedStatement.setString(2,email);
                preparedStatement.executeUpdate();

            }

            else if(s==4){

                String q1 = "UPDATE members SET Password=? WHERE Email=?;";
                preparedStatement = connection.prepareStatement(q1);
                preparedStatement.setString(1,reset.getPass1());
                preparedStatement.setString(2,email);
                preparedStatement.executeUpdate();

            }

            else if(s==5){
                String q1 = "UPDATE basic_users SET Password=? WHERE Email=?;";
                preparedStatement = connection.prepareStatement(q1);
                preparedStatement.setString(1,reset.getPass1());
                preparedStatement.setString(2,email);
                preparedStatement.executeUpdate();
            }


        }






        return true;

    }
}
