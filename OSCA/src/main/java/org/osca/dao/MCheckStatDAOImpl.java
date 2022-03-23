package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCheckStatDAOImpl implements MCheckStatDAO {
    public ArrayList<ArrayList<Double>> getDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income),MONTH(concert_date) AS month FROM song_income WHERE Member_id =? AND cancel_status = 0 AND YEAR(concert_date)=YEAR(CURRENT_DATE) GROUP BY month ORDER BY month desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1, uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Double> amount = new ArrayList<>();
        ArrayList<Double> month = new ArrayList<>();
        ArrayList<ArrayList<Double>> fial = new ArrayList<>();
        int ut = 0;
        while (resultSet.next()) {
//            ut = resultSet.getInt(1);
            System.out.println(amount);
            amount.add(resultSet.getDouble(1));
            month.add(resultSet.getDouble(2));
        }

        fial.add(amount);
        fial.add(month);

        System.out.println(fial);


        return fial;
    }
    public ArrayList<ArrayList<Double>> getYearDetails(int uid) throws SQLException, ClassNotFoundException {


        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income),YEAR(concert_date) AS year FROM song_income WHERE Member_id =? AND cancel_status = 0 GROUP BY year ORDER BY year desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

//        stmt.setInt(1, uid);
        stmt.setInt(1, uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<Double> amount = new ArrayList<>();
        ArrayList<Double> year = new ArrayList<>();
        ArrayList<ArrayList<Double>> fial = new ArrayList<>();

        while (resultSet.next()) {
//
            System.out.println(amount);
            amount.add(resultSet.getDouble(1));
            year.add(resultSet.getDouble(2));
        }

        fial.add(amount);
        fial.add(year);

        System.out.println(fial);


        return fial;
    }


}
