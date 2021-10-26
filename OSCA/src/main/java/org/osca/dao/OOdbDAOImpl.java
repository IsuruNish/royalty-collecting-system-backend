package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OOdbDAOImpl implements OOdbDAO {
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name,last_name, email, phone_number FROM officials WHERE Emp_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(resultSet.getString(1));
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
        }


        //basic users count
        q = "SELECT count(*) FROM basic_users ;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //member count
        q = "SELECT count(*) FROM members ;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //License req count
        q = "SELECT COUNT(*) FROM( SELECT * FROM concert WHERE status = 0 AND l_flag = 0) AS t;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //song req count
        q = "SELECT COUNT(*) FROM( SELECT * FROM song_requests WHERE status = 0 AND s_flag = 0) AS t;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }


        //member income
        q = "SELECT SUM(Fee_without_commission) FROM(SELECT Fee_without_commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND l_flag = 1 AND type = ? ) AS t;";
        stmt = connection.prepareStatement(q);
        stmt.setString(1,"Close");
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //Concert count
        q = "SELECT COUNT(*) FROM( SELECT * FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND l_flag = 1) AS t;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }



        double tot = 0;
        //osca income
        q = "SELECT SUM(Commission) FROM(SELECT Commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND l_flag = 1) AS t;";
        stmt = connection.prepareStatement(q);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            tot = resultSet.getInt(1);
        }

        q = "SELECT SUM(Fee_Without_Commission) FROM(SELECT Fee_Without_Commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND l_flag = 1 AND type = ? ) AS t;";
        stmt = connection.prepareStatement(q);
        stmt.setString(1,"Open");
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            tot += resultSet.getInt(1);
        }

        x.add(String.valueOf(tot));
        return x;
    }

    public String getOOName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name FROM officials WHERE Emp_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }
        return x;
    }

}
