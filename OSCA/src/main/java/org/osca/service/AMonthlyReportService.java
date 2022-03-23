package org.osca.service;

import org.osca.dao.AMonthlyReportsDAO;
import org.osca.dao.AMonthlyReportsDAOImpl;
import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.SQLException;
import java.util.ArrayList;

public class AMonthlyReportService {

    private AMonthlyReportsDAO aMonthlyReportsDAO;
    public AMonthlyReportService(){ aMonthlyReportsDAO = new AMonthlyReportsDAOImpl();}

    public ArrayList<ArrayList<String>> getIncomingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {
        return aMonthlyReportsDAO.getMonthlyIncomingDetails(aMonthlyReportsYearAndMonth);
    }

    public ArrayList<ArrayList<String>> getOutgoingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {
        return aMonthlyReportsDAO.getMonthlyOutgoingDetails(aMonthlyReportsYearAndMonth);
    }

    public ArrayList<ArrayList<String>> getReportTableDetails() throws SQLException, ClassNotFoundException {
        return aMonthlyReportsDAO.getReportTableData();
    }


}
