package org.osca.dao;

import org.osca.model.ForgetPassword;
import org.osca.model.ResetPassword;

import java.sql.SQLException;

public interface ResetPasswordDAO {

    public boolean findPin(ResetPassword reset) throws SQLException, ClassNotFoundException;
}
