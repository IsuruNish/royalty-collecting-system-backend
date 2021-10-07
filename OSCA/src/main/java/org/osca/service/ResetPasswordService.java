package org.osca.service;

import org.osca.dao.ForgetPasswordDAO;
import org.osca.dao.ForgetPasswordDAOImpl;
import org.osca.dao.ResetPasswordDAO;
//import org.osca.dao.ResetPasswordDAOImpl;
import org.osca.model.ForgetPassword;
import org.osca.model.ResetPassword;

import java.sql.SQLException;

public class ResetPasswordService {

    private ResetPasswordDAO resetPasswordDAO;

    public ResetPasswordService(){
        resetPasswordDAO = new ForgetPasswordDAOImpl();
    }

    public boolean addPin(ResetPassword reset) throws SQLException, ClassNotFoundException {
//        System.out.println(reset);
        return resetPasswordDAO.findPin(reset);
    }
}
