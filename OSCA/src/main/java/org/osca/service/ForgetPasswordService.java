package org.osca.service;

import org.osca.dao.ForgetPasswordDAO;
import org.osca.dao.ForgetPasswordDAOImpl;
import org.osca.dao.signupDAO;
import org.osca.dao.signupDAOimp;
import org.osca.model.ForgetPassword;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public class ForgetPasswordService {

    private ForgetPasswordDAO forgetPasswordDAO;

    public ForgetPasswordService(){
        forgetPasswordDAO = new ForgetPasswordDAOImpl();
    }

    public boolean addEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        return forgetPasswordDAO.findEmail(fp);
    }
}
