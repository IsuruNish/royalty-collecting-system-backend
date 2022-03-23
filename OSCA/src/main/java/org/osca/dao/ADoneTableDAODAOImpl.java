//package org.osca.dao;
//
//import org.osca.database.DBConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ADoneTableDAODAOImpl implements ADoneTableDAO{
//
//    @Override
//    public String getTableDetails(String year, String month) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getObj().getConnection();
//        String q="SELECT year,month FROM check_reports;";
//        PreparedStatement preparedStatement = connection.prepareStatement(q);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if(resultSet.next()){
//          year=resultSet.getString(1,year);
//          month=resultSet.getString(2,month);
//
//        }
//
//        return null;
//    }
//}
