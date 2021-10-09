package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.AAddUsers;
import org.osca.model.ShowOrganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AAddUsersDAOImpl implements AAddUsersDAO{

    public boolean addUsers(AAddUsers user,int uid) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getObj().getConnection();
//        String q="SELECT Emp_ID FROM officials WHERE email=?;";
//        PreparedStatement preparedStatement = connection.prepareStatement(q);
//        preparedStatement.setString(1,email);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        int s=-1;
//        if(resultSet.next()){
//            s=resultSet.getInt(1);
//        }
//
//        System.out.println(s);

//        Connection connection = DBConnection.getObj().getConnection();

        String q1 = "INSERT INTO officials (NIC, First_Name, Last_Name,Designation, Phone_Number, Email, Delete_Status,Password, User_Type,Created_By,Created_On) VALUE(?,?,?,?,?,?,?,?,?,?,CURRENT_DATE );";
        PreparedStatement preparedStatement = connection.prepareStatement(q1);
//        preparedStatement.setInt(1,ppl.getId());
//        preparedStatement.setInt(1,NULL);
        preparedStatement.setString(1,user.getNic());
        preparedStatement.setString(2,user.getFname());
        preparedStatement.setString(3,user.getLname());
        preparedStatement.setString(4,"osca-official");
        preparedStatement.setString(5,user.getPhone());
        preparedStatement.setString(6,user.getEmail());
        preparedStatement.setInt(7,0);
        preparedStatement.setString(8,"isuru");
        preparedStatement.setInt(9,3);
        preparedStatement.setInt(10,uid);
//        preparedStatement.setInt(10,100);
//        preparedStatement.setDate(11, Date.valueOf(java.time.LocalDate.now()));

        return preparedStatement.executeUpdate() > 0;

    }
}
