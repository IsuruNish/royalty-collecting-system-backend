package org.osca.dao;

import org.osca.controller.login.Mail;
import org.osca.database.DBConnection;
import org.osca.service.SAdashboardService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OOlicensePaymentDAOImpl implements OOlicensePaymentDAO{

    public ArrayList<ArrayList<String>> getLicensePaymentInfo() throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT concert_id, Concert_Name, user_id, concert_date, total_fee, type,  Payment_slip_link  FROM concert WHERE status = 2 AND rejected = 0 AND Payment_status = 0 AND cancelled = 0 AND Payment_slip_link IS NOT null;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        ResultSet resultSet = stmt.executeQuery();
        ResultSet rs;
        ArrayList<ArrayList<String>> finalOne = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<String> data = new ArrayList<>();

            String q4 = "SELECT first_name, last_name FROM basic_users WHERE user_id = ? ;";
            PreparedStatement stmt2 = connection.prepareStatement(q4);

            stmt2.setInt(1, resultSet.getInt(3));
            rs = stmt2.executeQuery();

            String fname = null;
            String lname = null;

            if (rs.next()) {
                fname = rs.getString(1);
                lname = rs.getString(2);
            }

            data.add(resultSet.getString(1));
            data.add(resultSet.getString(2));
            data.add(fname);
            data.add(lname);
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(5));
            data.add(resultSet.getString(6));
            data.add(resultSet.getString(7));
            data.add(resultSet.getString(3));

            finalOne.add(data);
        }

        return finalOne;
    }

    public Boolean paymentAccepted(int id) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET Payment_status = 1 WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);

        ArrayList<String> dataForEmail = getLicenseEmialDetails(id);
        Mail objMail = new Mail();
        SAdashboardService serviceSA = new SAdashboardService();
        String fulName = serviceSA.getShowOrganizerFULLName(Integer.parseInt(dataForEmail.get(6)));
        String emailSO = serviceSA.getShowOrganizerEmail(Integer.parseInt(dataForEmail.get(6)));
        objMail.licenseEmail("",fulName, dataForEmail, emailSO );

        return stmt.executeUpdate() > 0;
    }

    public Boolean paymentDeny(int id) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        Connection connection = DBConnection.getObj().getConnection();
        String q = "UPDATE concert SET Payment_slip_link = null WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q);

        stmt.setInt(1,id);

        return stmt.executeUpdate() > 0;
    }

    public ArrayList<String> getLicenseEmialDetails(int cid) throws SQLException, ClassNotFoundException{
        Connection connection = DBConnection.getObj().getConnection();
        String q3 = "SELECT user_id, concert_name, concert_date, venue, type, user_id FROM concert WHERE concert_id = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q3);

        stmt.setInt(1, cid);
        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> data = new ArrayList<>();

        if (resultSet.next()) {

            data.add(String.valueOf(cid));
            data.add(String.valueOf(resultSet.getInt(1)));
            data.add(resultSet.getString(2));
            data.add(resultSet.getString(5));
            data.add(resultSet.getString(4));
            data.add(resultSet.getString(3));
            data.add(resultSet.getString(6));
        }

        System.out.println(data);
        return data;
    }
}
