package org.osca.service;


import org.osca.dao.SAdbDAO;
import org.osca.dao.SAdbDAOImpl;
import org.osca.model.UserLoginModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class SAdashboardService {
    private SAdbDAO sadbDAO;

    public SAdashboardService(){
        sadbDAO = new SAdbDAOImpl();
    }

    public ArrayList<String> getDashboardDetails(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getDetails(uid);
    }

    public String getSuperadminName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getSAName(uid);
    }

    public String getMemberName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getMName(uid);
    }

    public String getShowOrganizerName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getSOName(uid);
    }


    public String getSuperadminFULLName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getSAFULLName(uid);
    }

    public String getMemberFULLName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getMFULLName(uid);
    }

    public String getShowOrganizerFULLName(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getSOFULLName(uid);
    }
}
