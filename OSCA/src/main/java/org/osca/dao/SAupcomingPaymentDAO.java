package org.osca.dao;

import org.osca.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SAupcomingPaymentDAO {

    public ArrayList<ArrayList<String>> getCancelledLicenses() throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getMemPayments() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getMemberInfo(int id) throws SQLException, ClassNotFoundException;
    public String getSongInfo(int id) throws SQLException, ClassNotFoundException;


    public Boolean setPaymentSO(int uid, int concertID) throws SQLException, ClassNotFoundException;
    public Boolean setPaymentMem(int uid, int concertID, int songID) throws SQLException, ClassNotFoundException;

}
