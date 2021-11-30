package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SAdbDAOImpl implements SAdbDAO {
    public ArrayList<String> getDetails(int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_type,first_name,last_name, email, phone_number FROM officials WHERE Emp_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> x = new ArrayList<>();
        int ut = 0;
        if(resultSet.next()){
            ut = resultSet.getInt(1);
            x.add(resultSet.getString(2));
            x.add(resultSet.getString(3));
            x.add(resultSet.getString(4));
            x.add(resultSet.getString(5));
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
        q = "SELECT COUNT(*) FROM( SELECT * FROM concert WHERE status = 1 AND rejected = 0 AND cancelled = 0) AS t;";
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
        q = "SELECT SUM(Fee_without_commission) FROM(SELECT Fee_without_commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND rejected = 0 AND type = ? AND cancelled = 0  AND status = 2) AS t;";
        stmt = connection.prepareStatement(q);
        stmt.setString(1,"Close");
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }

        //Concert count
        q = "SELECT COUNT(*) FROM( SELECT * FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND rejected = 0 AND cancelled = 0  AND status = 2) AS t;";
        stmt = connection.prepareStatement(q);

        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            x.add(String.valueOf(resultSet.getInt(1)));
        }



        double tot = 0;
        //osca income
        q = "SELECT SUM(Commission) FROM(SELECT Commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND rejected = 0) AS t;";
        stmt = connection.prepareStatement(q);
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            tot = resultSet.getInt(1);
        }

        q = "SELECT SUM(Fee_Without_Commission) FROM(SELECT Fee_Without_Commission FROM concert WHERE MONTH(concert_date) = MONTH(CURRENT_DATE) AND rejected = 0 AND type = ? AND cancelled = 0  AND status = 2) AS t;";
        stmt = connection.prepareStatement(q);
        stmt.setString(1,"Open");
        resultSet = stmt.executeQuery();

        if(resultSet.next()){
            tot += resultSet.getInt(1);
        }

        x.add(String.valueOf(tot));
        x.add(String.valueOf(ut));
        return x;
    }

    public String getSAName(int uid) throws SQLException, ClassNotFoundException{
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


    public String getMName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name FROM Members WHERE member_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }
        return x;
    }

    public String getSOName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name FROM basic_users WHERE user_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
        }
        return x;
    }



    public String getSAFULLName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name, last_name FROM officials WHERE Emp_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;
        String y = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
            y = resultSet.getString(2);
        }
        return x+" "+y;
    }


    public String getMFULLName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name, last_name FROM Members WHERE member_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;
        String y = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
            y = resultSet.getString(2);
        }
        return x+" "+y;
    }

    public String getSOFULLName(int uid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT first_name,last_name FROM basic_users WHERE user_id =?;";

        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,uid);

        ResultSet resultSet = stmt.executeQuery();

        String x = null;
        String y = null;

        if(resultSet.next()){
            x = resultSet.getString(1);
            y = resultSet.getString(2);
        }
        return x+" "+y;
    }

}
