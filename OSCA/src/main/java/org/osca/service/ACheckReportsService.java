package org.osca.service;

import org.osca.dao.ACheckReportsDAO;
import org.osca.dao.ACheckReportsDAOImpl;
import org.osca.dao.AMonthlyReportsDAO;
import org.osca.dao.AMonthlyReportsDAOImpl;
import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.SQLException;
import java.util.ArrayList;

public class ACheckReportsService {

    private ACheckReportsDAO aCheckReportsDAO;
    public ACheckReportsService(){ aCheckReportsDAO = new ACheckReportsDAOImpl();}

    public boolean getcheckDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException {
        return aCheckReportsDAO.getCheckOutgoingDetails(aMonthlyReportsYearAndMonth);
    }
}
