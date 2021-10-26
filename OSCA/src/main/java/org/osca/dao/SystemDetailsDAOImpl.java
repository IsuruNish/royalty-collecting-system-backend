package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.SystemDetails;

import java.sql.*;

public class SystemDetailsDAOImpl implements SystemDetailsDAO {
    public boolean changeCommision(SystemDetails detail) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT New_Value FROM system_details WHERE Key_id = 1;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        Double x = 0.0;

        if(resultSet.next()) {
            x = resultSet.getDouble(1);
        }

        String q1 = "UPDATE system_details SET New_Value = ? , Prev_Value = ? , starting_date = ? , Updated_On = CURRENT_DATE WHERE Key_id = 1 ;";
        stmt = connection.prepareStatement(q1);

        stmt.setDouble(1,detail.getCommision());
        stmt.setDouble(2,x);

        stmt.setDate(3, java.sql.Date.valueOf(detail.getCommisionDate()));

        return stmt.executeUpdate() > 0;
    }

    public boolean licenseCancellation(SystemDetails detail) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT New_Value FROM system_details WHERE Key_id = 2;";
        PreparedStatement stmt = connection.prepareStatement(q);
        ResultSet resultSet = stmt.executeQuery();

        double x = 0;

        if(resultSet.next()) {
            x = (int) resultSet.getDouble(1);
        }


        String q1 = "UPDATE system_details SET New_Value = ? , Prev_Value = ? , starting_date = ? , Updated_On = CURRENT_DATE WHERE Key_id = 2 ;";
        stmt = connection.prepareStatement(q1);

        stmt.setDouble(1,detail.getCancellationDuration());
        stmt.setDouble(2,x);
        stmt.setDate(3,  java.sql.Date.valueOf(detail.getCancellationDurationDate()));

        return stmt.executeUpdate() > 0;
    }

    public boolean licenseCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT New_Value FROM system_details WHERE Key_id = 3;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        Double x = 0.0;

        if(resultSet.next()) {
            x = resultSet.getDouble(1);
        }


        String q1 = "UPDATE system_details SET New_Value = ? , Prev_Value = ? , starting_date = ? , Updated_On = CURRENT_DATE WHERE Key_id = 3 ;";
        stmt = connection.prepareStatement(q1);

        stmt.setDouble(1,detail.getCancellationFee());
        stmt.setDouble(2,x);
        stmt.setDate(3, java.sql.Date.valueOf(detail.getCancellationFeeDate()));

        return stmt.executeUpdate() > 0;
    }

    public double getCommisionValue(SystemDetails detail) throws SQLException, ClassNotFoundException {
        double x = 0.0;
        String q1 = null;
        String q2 = null;

        Connection connection = DBConnection.getObj().getConnection();
        q1 = "SELECT prev_value FROM system_details WHERE starting_date > CURRENT_DATE AND key_id = 1; ";

        PreparedStatement stmt = connection.prepareStatement(q1);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        q2 = "SELECT new_value FROM system_details WHERE starting_date <= CURRENT_DATE AND key_id = 1; ";
        stmt = connection.prepareStatement(q2);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        return x;
    }


    public double getlicenseCancellationDays(SystemDetails detail) throws SQLException, ClassNotFoundException {
        double x = 0.0;
        String q1 = null;
        String q2 = null;

        Connection connection = DBConnection.getObj().getConnection();
        q1 = "SELECT prev_value FROM system_details WHERE starting_date > CURRENT_DATE AND key_id = 2; ";

        PreparedStatement stmt = connection.prepareStatement(q1);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        q2 = "SELECT new_value FROM system_details WHERE starting_date <= CURRENT_DATE AND key_id = 2; ";
        stmt = connection.prepareStatement(q2);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        return x;
    }



    public double getCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException {
        double x = 0.0;
        String q1 = null;
        String q2 = null;

        Connection connection = DBConnection.getObj().getConnection();
        q1 = "SELECT prev_value FROM system_details WHERE starting_date > CURRENT_DATE AND key_id = 3; ";

        PreparedStatement stmt = connection.prepareStatement(q1);
        ResultSet resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        q2 = "SELECT new_value FROM system_details WHERE starting_date <= CURRENT_DATE AND key_id = 3; ";
        stmt = connection.prepareStatement(q2);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x = resultSet.getDouble(1);
        }

        return x;
    }

}
