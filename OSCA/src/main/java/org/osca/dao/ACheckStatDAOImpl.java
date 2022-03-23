package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ACheckStatDAOImpl implements ACheckStatDAO{
    public ArrayList<ArrayList<Double>> getDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(Total_Fee-Cancellation_Fee) as dif, MONTH(Date_Applied) AS month FROM concert WHERE YEAR(Date_Applied)=YEAR(CURRENT_DATE) GROUP BY month order by month desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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
        String q = "SELECT SUM(Total_Fee-Cancellation_Fee) as dif, YEAR(Date_Applied) AS year FROM concert GROUP BY year order by year desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

//        stmt.setInt(1, uid);
        //stmt.setInt(1, uid);

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

    public ArrayList<ArrayList<Double>> getMemberIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income),MONTH(concert_date) AS month FROM song_income WHERE cancel_status = 0 AND YEAR(concert_date)=YEAR(CURRENT_DATE) GROUP BY month ORDER BY month desc;";
        String q1 = "SELECT SUM(Commission) as commission, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 AND Cancelled = 0 GROUP BY month order by month desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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

    public ArrayList<ArrayList<Double>> getMemberIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income),YEAR(concert_date) AS year FROM song_income WHERE cancel_status = 0 GROUP BY year ORDER BY year desc;";
        String q1 = "SELECT SUM(Commission) as commission, YEAR(Date_Applied) AS year FROM concert WHERE Status = 2 AND Cancelled = 0 GROUP BY year order by year desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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

    public ArrayList<ArrayList<Double>> getLicenseIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "SELECT SUM(Cancellation_Fee) as fee, YEAR(Date_Applied) AS year FROM concert WHERE Status = 2 AND Cancelled = 1 GROUP BY year order by year desc;";
       // String q = "SELECT SUM(Commission) as commission, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 AND Cancelled = 0 GROUP BY month order by month desc;";
       // String q = "SELECT SUM(Commission+Cancellation_Fee) as commission, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 GROUP BY month order by month desc;";
        String q = "SELECT SUM(tot), YEAR(dawasa) FROM (SELECT Commission AS tot, Date_Applied AS dawasa from concert WHERE Status = 2 AND Cancelled = 0 UNION SELECT Cancellation_Fee AS tot, Date_Cancelled AS dawasa from concert WHERE Cancelled = 1) AS t GROUP by YEAR(dawasa) ORDER BY YEAR(dawasa) desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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
    public ArrayList<ArrayList<Double>> getLicenseIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
//        String q = "SELECT SUM(Commission) as commission, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 AND Cancelled = 0 GROUP BY month order by month desc;";
        //String q = "SELECT SUM(Commission+Cancellation_Fee) as commission, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 GROUP BY month order by month desc;";
        String q = "SELECT SUM(tot), MONTH(dawasa) FROM (SELECT Commission AS tot, Date_Applied AS dawasa from concert WHERE Status = 2 AND Cancelled = 0 AND YEAR(Date_Applied)=YEAR(CURRENT_DATE) UNION SELECT Cancellation_Fee AS tot, Date_Cancelled AS dawasa from concert WHERE Cancelled = 1) AS t GROUP by month(dawasa) ORDER BY month(dawasa) desc;";
//        String q1 = "SELECT SUM(Cancellation_Fee) as fee, MONTH(Date_Applied) AS month FROM concert WHERE Status = 2 AND Cancelled = 1 GROUP BY month order by month desc;";

        PreparedStatement stmt = connection.prepareStatement(q);
//        PreparedStatement stmt1 = connection.prepareStatement(q1);

        //stmt.setInt(1, uid);

        ResultSet resultSet = stmt.executeQuery();
//        ResultSet resultSet1 = stmt1.executeQuery();

        ArrayList<Double> amount = new ArrayList<>();
//        ArrayList<Double> amount1 = new ArrayList<>();

        ArrayList<Double> month = new ArrayList<>();
//        ArrayList<Double> month1 = new ArrayList<>();

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
    public ArrayList<ArrayList<Double>>getDistributionReportMonthDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income) as income, MONTH(concert_date) AS month FROM song_income WHERE cancel_status =0 AND is_paid =1 AND YEAR(concert_date)=YEAR(CURRENT_DATE) GROUP BY month order by month desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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
    public ArrayList<ArrayList<Double>> getDistributionReportYearDetails(int uid) throws SQLException, ClassNotFoundException {

        //System.out.println("hello");

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT SUM(income) as income, YEAR(concert_date) AS year FROM song_income WHERE cancel_status =0 AND is_paid =1 GROUP BY year order by year desc;";

        PreparedStatement stmt = connection.prepareStatement(q);

        //stmt.setInt(1, uid);

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

}
