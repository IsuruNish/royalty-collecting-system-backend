package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ACheckStatDAO {


    public ArrayList<ArrayList<Double>>  getDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getYearDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getMemberIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getMemberIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getLicenseIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getLicenseIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getDistributionReportMonthDetails(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Double>>  getDistributionReportYearDetails(int uid) throws SQLException, ClassNotFoundException;
}
