package org.osca.dao;

import org.osca.model.AMonthlyReportsYearAndMonth;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ACheckReportsDAO {
    public boolean getCheckOutgoingDetails(AMonthlyReportsYearAndMonth aMonthlyReportsYearAndMonth) throws SQLException, ClassNotFoundException;
}
