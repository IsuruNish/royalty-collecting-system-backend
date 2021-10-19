package org.osca.service;



import org.osca.dao.ForgotPasswordDAO;
import org.osca.dao.ForgotPasswordDAOImpl;
import org.osca.model.ForgetPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public class ForgotPasswordService {

    private ForgotPasswordDAO obj;

    public ForgotPasswordService(){
        obj = new ForgotPasswordDAOImpl();
    }

    public ArrayList<Integer> checkEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        return obj.findEmail(fp);
    }

    public boolean sendOTP(ForgetPassword fp, int uid, int ut) throws SQLException, ClassNotFoundException {
        return obj.emailTheOTP(fp, uid, ut);
    }

    public boolean checkIf2minsHavePassed(ForgetPassword fp, int uid) throws SQLException, ClassNotFoundException {
        return obj.findTime(fp, uid);
    }

    public boolean checkIfTheresArecord(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        return obj.findRecord(fp);
    }

    public boolean changePassword(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        return obj.changePass(fp);
    }

    public int checkVerification(ForgetPassword fp) throws SQLException, ClassNotFoundException {
        return obj.verify(fp);
    }

}
