package org.osca.service;

import org.osca.dao.LoginDAO;
import org.osca.dao.LoginDAOImpl;
import org.osca.model.UserLoginModel;

import java.sql.SQLException;

public class LoginService {
    private LoginDAO loginDAO;

    public LoginService(){
        loginDAO=new LoginDAOImpl();
    }

    public UserLoginModel getUser(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        return loginDAO.login(userLoginModel);
    }

    public int getID(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        return loginDAO.getUserID(userLoginModel);
    }

    public UserLoginModel getUser2(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        return loginDAO.login2(userLoginModel);
    }

    public int getID2(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        return loginDAO.getUserID2(userLoginModel);
    }
}
