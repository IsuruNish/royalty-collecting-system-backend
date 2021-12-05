package org.osca.dao;

import org.osca.model.UserLoginModel;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginDAO {
    UserLoginModel login (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
    UserLoginModel login2 (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;

    int getUserID (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;
    int getUserID2 (UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException ;

    ArrayList<Integer> getUserIDusingPin(int pin) throws SQLException, ClassNotFoundException;

    int emailVerified(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException;
    public boolean verifyEmailSO(int pin) throws SQLException, ClassNotFoundException;
    public boolean verifyEmailMem(int pin) throws SQLException, ClassNotFoundException;
    public boolean verifyEmailEmp(int pin) throws SQLException, ClassNotFoundException;

    public boolean checkTimeForEmail(int pin) throws SQLException, ClassNotFoundException;

    public int setEmailIDForSO(int uid) throws SQLException, ClassNotFoundException;

    }
