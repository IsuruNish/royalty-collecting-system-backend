package org.osca.dao;

import org.osca.model.UserLoginModel;

import java.sql.SQLException;

public interface LoginDAO {
    UserLoginModel login (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
    UserLoginModel login2 (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;

    int getUserID (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
    int getUserID2 (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
}
