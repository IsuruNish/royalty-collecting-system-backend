package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ACheckReportsDAOImpl implements ACheckReportsDAO{

    @Override
    public boolean getCheckOutgoingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {
        String year = aMonthlyReportsYearAndMonth.getYear();
        String month=aMonthlyReportsYearAndMonth.getMonth();



        Connection connection = DBConnection.getObj().getConnection();

        String q10="SELECT * FROM check_reports WHERE year =? and month=? ;";
        PreparedStatement stmt_q10=connection.prepareStatement(q10);
        stmt_q10.setString(1,year);
        stmt_q10.setString(2,month);
        ResultSet resultSet=stmt_q10.executeQuery();
        if(!resultSet.next()){
            String checked = "INSERT INTO check_reports VALUES (?,?);";
            PreparedStatement check_stmt = connection.prepareStatement(checked);
            check_stmt.setString(1,year);
            check_stmt.setString(2,month);
//            check_stmt.setBoolean(3,false);
            int ch=check_stmt.executeUpdate();
            System.out.println("checking" + ch);

        }
//        String checked = "UPDATE check_reports SET is_checked=? where year=? and month =?;";
//        PreparedStatement check_stmt = connection.prepareStatement(checked);

//        check_stmt.setBoolean(1,true);
//        check_stmt.setString(2,year);
//        check_stmt.setString(3,month);
//        int ch=check_stmt.executeUpdate();
//        System.out.println("checking" + ch);
        return true;
    }
}
