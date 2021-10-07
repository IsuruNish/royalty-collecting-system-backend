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

        String q = "SELECT email AS email FROM officials WHERE email=? UNION SELECT email AS email FROM members WHERE email=? UNION SELECT email AS email FROM basic_users WHERE email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);


        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3,user.getEmail());



        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return false;
        }



        String query = "INSERT INTO basic_users (NIC, First_Name, Last_Name, Phone_Number, Email, Delete_Status,Password, User_Type) VALUE(?,?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);

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

    public int getBasicUserID(ShowOrganizer user) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String query = "SELECT user_id FROM basic_users WHERE first_name = ? AND last_name =? AND email=?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,user.getFname());
        preparedStatement.setString(2,user.getLname());
        preparedStatement.setString(3,user.getEmail());


        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        else {
            return 0;
        }


    }
}
