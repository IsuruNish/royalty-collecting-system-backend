package org.osca.service;

import org.osca.dao.OOdbDAO;
import org.osca.dao.OOdbDAOImpl;
import org.osca.model.UserLoginModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class OOdashboardService {
    private OOdbDAO oodbDAO;

    public OOdashboardService(){
        oodbDAO = new OOdbDAOImpl();
    }

    public ArrayList<String> getDashboardDetails(int uid) throws SQLException, ClassNotFoundException {
        return oodbDAO.getDetails(uid);
    }

    public String getOfficialName(int uid) throws SQLException, ClassNotFoundException {
        return oodbDAO.getOOName(uid);
    }
}


