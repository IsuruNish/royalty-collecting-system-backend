package org.osca.dao;

import org.osca.model.SystemDetails;

import java.sql.SQLException;

public interface SystemDetailsDAO {
    public boolean changeCommision(SystemDetails detail) throws SQLException, ClassNotFoundException;

    public boolean licenseCancellation(SystemDetails detail) throws SQLException, ClassNotFoundException;

    public boolean licenseCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException;

    public double getCommisionValue(SystemDetails detail) throws SQLException, ClassNotFoundException;

    public double getlicenseCancellationDays(SystemDetails detail) throws SQLException, ClassNotFoundException;

    public double getCancellationFee(SystemDetails detail) throws SQLException, ClassNotFoundException;

}
