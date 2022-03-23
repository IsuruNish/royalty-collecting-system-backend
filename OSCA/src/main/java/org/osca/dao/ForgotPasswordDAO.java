package org.osca.dao;

import org.osca.model.ForgetPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ForgotPasswordDAO {
    public ArrayList<Integer> findEmail(ForgetPassword fp) throws SQLException, ClassNotFoundException;
    public boolean emailTheOTP(ForgetPassword fp, int uid, int ut) throws SQLException, ClassNotFoundException;
    public boolean findTime(ForgetPassword fp, int uid) throws SQLException, ClassNotFoundException;
    public boolean findRecord(ForgetPassword fp) throws SQLException, ClassNotFoundException;
    public boolean changePass(ForgetPassword fp) throws SQLException, ClassNotFoundException;
    public int verify(ForgetPassword fp) throws SQLException, ClassNotFoundException;

}
