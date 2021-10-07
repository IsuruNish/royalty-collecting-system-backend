package org.osca.dao;

import org.osca.model.ForgetPassword;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public interface ForgetPasswordDAO {

    public boolean findEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException;
}
