package org.osca.dao;

import org.osca.model.UserLoginModel;

import java.sql.SQLException;

public interface LoginDAO {
    UserLoginModel login (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
}
