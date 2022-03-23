package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.SuperAdminDashboard;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SAchangeinfoDAO {
    public ArrayList<String> getUserDetails(int uid) throws SQLException, ClassNotFoundException;

    public boolean updateUserDetails(int uid,  SuperAdminDashboard user) throws SQLException, ClassNotFoundException;

    public boolean updateProflePic(int uid, String url) throws SQLException, ClassNotFoundException;

    public boolean daleteImage(int uid) throws SQLException, ClassNotFoundException;

    public boolean updatePassword(int uid, String oldPass, String newPass) throws SQLException, ClassNotFoundException;


    public boolean setEmailVerificationForEmp(int uid) throws SQLException, ClassNotFoundException;
    public boolean setEmailVerificationForSO(int uid) throws SQLException, ClassNotFoundException;
    public boolean setEmailVerificationForMem(int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllEmails(int uid) throws SQLException, ClassNotFoundException;

}
