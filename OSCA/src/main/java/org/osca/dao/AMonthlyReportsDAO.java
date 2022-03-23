package org.osca.dao;

import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AMonthlyReportsDAO {
    public ArrayList<ArrayList<String>>  getMonthlyIncomingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>>  getMonthlyOutgoingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>>  getReportTableData() throws SQLException, ClassNotFoundException;
}
