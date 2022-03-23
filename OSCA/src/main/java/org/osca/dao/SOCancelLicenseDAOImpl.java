package org.osca.dao;


import org.osca.database.DBConnection;
import org.osca.model.CancelLicense;
import org.osca.model.ConcertIDModel;
import org.osca.model.SOCancelLicense;
import org.osca.model.SystemDetails;
import org.osca.service.SystemDetailsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SOCancelLicenseDAOImpl implements SOCancelLicenseDAO{

    @Override
    public boolean getIDs(ConcertIDModel concertIDModel) throws SQLException, ClassNotFoundException {


        SystemDetailsService service = new SystemDetailsService();
        SystemDetails details = new SystemDetails();
        double cancellationFee = service.getLicenseCancellationFee(details);
        int concertID = concertIDModel.getConcertID();

        System.out.println(concertID);

        Connection connection = DBConnection.getObj().getConnection();

        String q = "UPDATE concert SET Cancelled = 1, Date_Cancelled = CURRENT_DATE , Cancellation_Fee = ? WHERE Concert_ID = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setDouble(1,cancellationFee);
        preparedStatement.setInt(2,concertID);
        boolean a = preparedStatement.executeUpdate() > 0;

        String q1 = "UPDATE song_income SET cancel_status = 1 WHERE Concert_ID = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(q1);
        preparedStatement1.setInt(1,concertID);
        boolean b = preparedStatement1.executeUpdate() > 0;

        System.out.println(a);
        System.out.println(b);


        return a && b;

    }

    @Override
    public ArrayList<ArrayList<String>> getDetails(int uid) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT Concert_ID,Concert_Name,Concert_Date,Venue,Type,Total_Fee FROM concert WHERE User_ID=? AND Rejected = 0 AND Cancelled = 0 AND Date(CURRENT_DATE + ?) <= Concert_Date;";
        PreparedStatement stmt = connection.prepareStatement(q);
        SystemDetailsService service = new SystemDetailsService();
        SystemDetails details = new SystemDetails();
        double noOfDays = service.getLicenseCancellationDuration(details);
        double cancellationFee = service.getLicenseCancellationFee(details);
        stmt.setInt(1,uid);
        stmt.setDouble(2,noOfDays);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> venues = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> fees = new ArrayList<>();



        while (resultSet.next()){

            ids.add(resultSet.getString(1));
            names.add(resultSet.getString(2));
            dates.add(resultSet.getString(3));
            venues.add(resultSet.getString(4));
            types.add(resultSet.getString(5));
            fees.add(String.valueOf(Double.parseDouble(resultSet.getString(6))-cancellationFee));
        }

        ArrayList<ArrayList<String>> tot = new ArrayList<>();
        tot.add(ids);
        tot.add(names);
        tot.add(dates);
        tot.add(venues);
        tot.add(types);
        tot.add(fees);

        System.out.println(tot);
        return tot;
    }
}
