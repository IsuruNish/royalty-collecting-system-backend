package org.osca.service;

import org.osca.dao.ACheckStatDAO;
import org.osca.dao.ACheckStatDAOImpl;
import org.osca.dao.MCheckStatDAO;
import org.osca.dao.MCheckStatDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ACheckStatService {
    private ACheckStatDAO aCheckStatDAO;

    public ACheckStatService(){
        aCheckStatDAO = new ACheckStatDAOImpl();
    }

    public ArrayList<ArrayList<Double>> getPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getDetails(uid);
    }

    public ArrayList<ArrayList<Double>> getYearPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getYearDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getMemberIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getMemberIncomeMonthDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getMemberIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getMemberIncomeYearDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getLicenseIncomeYearDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getLicenseIncomeYearDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getLicenseIncomeMonthDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getLicenseIncomeMonthDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getDistributionReportMonthDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getDistributionReportMonthDetails(uid);
    }
    public ArrayList<ArrayList<Double>> getYDistributionReportYearDetails(int uid) throws SQLException, ClassNotFoundException {
        return aCheckStatDAO.getDistributionReportYearDetails(uid);
    }
}
