package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.ShowOrganizer;

import java.sql.*;

public class signupDAOimp implements signupDAO {

//    public ArrayList<Show_Organizer> getAllPeople() throws SQLException, ClassNotFoundException {
//        ArrayList<Show_Organizer> ppl = new ArrayList<>();
//
//        Connection connection = DBcon.getObj().getConnecton();
//        String query = "SELECT * FROM people";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while(resultSet.next()){
//
//            if(resultSet != null){
//                ppl.add(new Show_Organizer(
//                                resultSet.getInt(1),
//                                resultSet.getString(2),
//                                resultSet.getString(3)
//                        )
//                );
//            }
//        }
//        return ppl;
//    }


    public boolean addSOs(ShowOrganizer user) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getObj().getConnection();

        String query = "INSERT INTO basic_user (NIC, First_Name, Last_Name, Phone_Number, Email, Delete_Status,Password, User_Type) VALUE(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1,ppl.getId());
//        preparedStatement.setInt(1,NULL);
        preparedStatement.setString(1,user.getNic());
        preparedStatement.setString(2,user.getFname());
        preparedStatement.setString(3,user.getLname());
        preparedStatement.setString(4,user.getPhone());
        preparedStatement.setString(5,user.getEmail());
        preparedStatement.setInt(6,0);
        preparedStatement.setString(7,user.getPassword());
        preparedStatement.setInt(8,5);
//        preparedStatement.setInt(10,100);
//        preparedStatement.setDate(11, Date.valueOf(java.time.LocalDate.now()));

        return preparedStatement.executeUpdate() > 0;

    }
}
