package org.osca.service;

import org.osca.dao.LoginDAO;
import org.osca.dao.LoginDAOImpl;
import org.osca.model.UserLoginModel;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Integer> getUserIDfromPin(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.getUserIDusingPin(pin);
    }

    public int isEmailVerified(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        return loginDAO.emailVerified(userLoginModel);
    }

    public boolean verifyEmailShowORganizer(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.verifyEmailSO(pin);
    }
    public boolean verifyEmailMember(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.verifyEmailMem(pin);
    }
    public boolean verifyEmailOfficial(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.verifyEmailEmp(pin);
    }

    public boolean checkEmailSendTime(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.checkTimeForEmail(pin);
    }

    public int setEmailIDForShowOrganizers(int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.setEmailIDForSO(pin);
    }


    public boolean set2FactorAuth(int uid, int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.set2Factor(uid, pin);
    }
    public boolean get2FactorAuth(int uid, int pin) throws SQLException, ClassNotFoundException {
        return loginDAO.get2Factor(uid,pin);
    }

}
