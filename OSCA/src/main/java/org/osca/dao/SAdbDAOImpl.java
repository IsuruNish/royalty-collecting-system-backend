package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SAdbDAOImpl implements SAdbDAO {
    public ArrayList<String> getDetails(String fname, String lname, String email) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT emp_id,phone_number FROM officials WHERE email=? AND first_name=? AND last_name=? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setString(1,email);
        stmt.setString(2, fname);
        stmt.setString(3,lname);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
            x.add(resultSet.getString(2));
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
        q = "SELECT COUNT(*) FROM( SELECT * FROM concert WHERE status = 1 AND l_flag = 0) AS t;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //song req count
        q = "SELECT COUNT(*) FROM( SELECT * FROM song_requests WHERE status = 1 AND s_flag = 0) AS t;";
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
}
