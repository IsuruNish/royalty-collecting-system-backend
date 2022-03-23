package org.osca.service;

import org.osca.dao.OOdbDAO;
import org.osca.dao.OOdbDAOImpl;
import org.osca.dao.SystemDetailsDAO;
import org.osca.dao.SystemDetailsDAOImpl;
import org.osca.model.SystemDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class SystemDetailsService {
    private SystemDetailsDAO userDAO;

    public SystemDetailsService(){
        userDAO = new SystemDetailsDAOImpl();
    }

    public boolean changeCommisionPercentage(SystemDetails detail) throws SQLException, ClassNotFoundException, SQLException {
        return userDAO.changeCommision(detail);
    }

    public boolean changeLicenseCancellationDuration(SystemDetails detail) throws SQLException, ClassNotFoundException {
        return userDAO.licenseCancellation(detail);
    }

    public boolean changeLicenseCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException, SQLException {
        return userDAO.licenseCancellationFee(detail);
    }

    public double getCommisionPercentage(SystemDetails detail) throws SQLException, ClassNotFoundException {
        return userDAO.getCommisionValue(detail);
    }

    public double getLicenseCancellationDuration(SystemDetails detail) throws SQLException, ClassNotFoundException {
        return userDAO.getlicenseCancellationDays(detail);
    }

    public double getLicenseCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException {
        return userDAO.getCancellationFee(detail);
    }
}
