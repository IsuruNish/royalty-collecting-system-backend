package org.osca.dao;

import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import org.osca.database.DBConnection;
import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AMonthlyReportsDAOImpl implements AMonthlyReportsDAO{


    @Override
    public ArrayList<ArrayList<String>> getMonthlyIncomingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {

        String year = aMonthlyReportsYearAndMonth.getYear();
        String month=aMonthlyReportsYearAndMonth.getMonth();
        System.out.println(year+""+month);
//        int type=aMonthlyReportsYearAndMonth.getType();
        aMonthlyReportsYearAndMonth.getMonth();
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "SELECT User_ID, Concert_Name, Total_Fee-Fee_without_commission as tot From concert WHERE Type='Close' and Cancelled =0 and status =2 and Rejected = 0 and YEAR(Concert_Date)=? and MONTH(Concert_Date)=?;";
        PreparedStatement stmt1 = connection.prepareStatement(q1);
        stmt1.setString(1,year);
        stmt1.setString(2,month);
        ResultSet resultSet1 = stmt1.executeQuery();
        String q2 = "SELECT User_ID, Concert_Name, Total_Fee as tot From concert WHERE Type='Open' and Cancelled =0 and status =2 and Rejected = 0 and YEAR(Concert_Date)=? and MONTH(Concert_Date)=? ;";
        PreparedStatement stmt2 = connection.prepareStatement(q2);
        stmt2.setString(1,year);
        stmt2.setString(2,month);
        ResultSet resultSet2 = stmt2.executeQuery();

        String q3 = "SELECT User_ID, Concert_Name, Cancellation_Fee as tot From concert WHERE Cancelled =1 and YEAR(Concert_Date)=? and MONTH(Concert_Date)=?;";
        PreparedStatement stmt3 = connection.prepareStatement(q3);
        stmt3.setString(1,year);
        stmt3.setString(2,month);
        ResultSet resultSet3 = stmt3.executeQuery();


        ArrayList<String> user_id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> tot = new ArrayList<>();
//        ArrayList<String> banks = new ArrayList<>();
        ArrayList<String> concert_names = new ArrayList<>();
        ArrayList<String> concert_dates = new ArrayList<>();
        ArrayList<ArrayList<String>> fial = new ArrayList<>();

        while (resultSet1.next()) {


            user_id.add(resultSet1.getString(1));
            String id =resultSet1.getString(1);
            concert_names.add(resultSet1.getString(2));
            tot.add(resultSet1.getString(3));
//            String q4="SELECT CONCAT(First_Name,\"\",Last_Name),Phone_Number FROM basic_users WHERE User_ID = ? UNION SELECT CONCAT(First_Name,Last_Name),Phone_Number FROM members WHERE Member_ID = ?;";
            String q4="SELECT CONCAT(First_Name,\" \",Last_Name),Phone_Number FROM basic_users WHERE User_ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(q4);
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name.add(resultSet.getString(1));
                phone.add(resultSet.getString(2));
            }



        }

        while (resultSet2.next()) {


            user_id.add(resultSet2.getString(1));
            String id =resultSet2.getString(1);
            concert_names.add(resultSet2.getString(2));
            tot.add(resultSet2.getString(3));
            String q4="SELECT CONCAT(First_Name,\" \",Last_Name),Phone_Number FROM basic_users WHERE User_ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(q4);
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
                name.add(resultSet.getString(1));
                phone.add(resultSet.getString(2));
            }


        }

        while (resultSet3.next()) {


            user_id.add(resultSet3.getString(1));
            String id =resultSet3.getString(1);
            String q4="SELECT CONCAT(First_Name,\" \",Last_Name),Phone_Number FROM basic_users WHERE User_ID = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(q4);
            preparedStatement.setString(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
                name.add(resultSet.getString(1));
                phone.add(resultSet.getString(2));
            }
            concert_names.add(resultSet3.getString(2));
            tot.add(resultSet3.getString(3));

        }


        fial.add(user_id);
        fial.add(name);
        fial.add(phone);
        fial.add(tot);
        fial.add(concert_names);

        System.out.println(fial);

        System.out.println(2);
        return fial;

    }

    @Override
    public ArrayList<ArrayList<String>> getMonthlyOutgoingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {


        String year = aMonthlyReportsYearAndMonth.getYear();
        String month=aMonthlyReportsYearAndMonth.getMonth();
        System.out.println(year+" "+month);
        Connection connection = DBConnection.getObj().getConnection();
        String q1="SELECT Member_ID,Concert_ID,income as tot From song_income WHERE cancel_status = 0 and delete_flag =0 and YEAR(concert_date)=? and MONTH(concert_date)=?;";

        PreparedStatement stmt1 = connection.prepareStatement(q1);
        stmt1.setString(1,year);
        stmt1.setString(2,month);

        ResultSet resultSet1 = stmt1.executeQuery();

        String q2="SELECT User_ID, Concert_Name, Fee_without_commission-Cancellation_Fee as tot From concert WHERE cancelled =1 and YEAR(Concert_Date)=? and MONTH(Concert_Date)=?;";
        PreparedStatement stmt2 = connection.prepareStatement(q2);
        stmt2.setString(1,year);
        stmt2.setString(2,month);
        ResultSet resultSet2 = stmt2.executeQuery();

        ArrayList<String>  memberOrUser_id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> tot = new ArrayList<>();
        ArrayList<String> banks = new ArrayList<>();
        ArrayList<String> concert_names = new ArrayList<>();
        ArrayList<ArrayList<String>> fial = new ArrayList<>();

        while (resultSet1.next()) {


            memberOrUser_id.add(resultSet1.getString(1));
            String id =resultSet1.getString(1);
//            concert_names.add(resultSet1.getString(2));
            String concert_id = resultSet1.getString(2);
            tot.add(resultSet1.getString(3));
            String q4="SELECT CONCAT(First_Name,\" \",Last_Name),Phone_Number, CONCAT(Bank_No,\" \",Bank_Name,\" \",Bank_Branch) FROM members WHERE Member_ID = ? UNION SELECT CONCAT(First_Name,\"\",Last_Name),Phone_Number,NULL FROM basic_users WHERE User_ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(q4);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,id);



            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name.add(resultSet.getString(1));
                phone.add(resultSet.getString(2));
                banks.add(resultSet.getString(3));
            }

            String q5 = "SELECT Concert_Name FROM concert WHERE Concert_ID=?;";
            PreparedStatement stmt_q5=connection.prepareStatement(q5);
            stmt_q5.setString(1,concert_id);
            ResultSet set =stmt_q5.executeQuery();
            if(set.next()){
                concert_names.add(set.getString(1));
            }




        }

        while (resultSet2.next()) {


            memberOrUser_id.add(resultSet2.getString(1));
            String id =resultSet2.getString(1);
//            concert_names.add(resultSet2.getString(2));
            String concert_id = resultSet2.getString(2);
            tot.add(resultSet2.getString(3));
            String q4="SELECT CONCAT(First_Name,\" \",Last_Name),Phone_Number, CONCAT(Bank_No,\" \",Bank_Name,\" \",Bank_Branch) FROM members WHERE Member_ID = ? UNION SELECT CONCAT(First_Name,\"\",Last_Name),Phone_Number,NULL FROM basic_users WHERE User_ID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(q4);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                name.add(resultSet.getString(1));
                phone.add(resultSet.getString(2));
                banks.add(resultSet.getString(3));
            }

            String q5 = "SELECT Concert_Name FROM concert WHERE Concert_ID=?;";
            PreparedStatement stmt_q5=connection.prepareStatement(q5);
            stmt_q5.setString(1,concert_id);
            ResultSet set =stmt_q5.executeQuery();
            if(set.next()){
                concert_names.add(set.getString(1));
            }




        }

        fial.add(memberOrUser_id);
        fial.add(name);
        fial.add(phone);
        fial.add(tot);
        fial.add(concert_names);
        fial.add(banks);

        System.out.println(fial);




        return fial;
    }

    public ArrayList<ArrayList<String>> getReportTableData() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        String q1 = "SELECT * From check_reports ;";
        PreparedStatement stmt1 = connection.prepareStatement(q1);
        ResultSet resultSet1 = stmt1.executeQuery();

        ArrayList<ArrayList<String>> finalArray = new ArrayList<>();
        while(resultSet1.next()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(resultSet1.getString(1));
            temp.add(resultSet1.getString(2));

            finalArray.add(temp);
        }
        return finalArray;
    }
}
