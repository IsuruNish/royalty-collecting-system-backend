package org.osca.service;

import org.osca.dao.AdbDAO;
import org.osca.dao.AdbDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdashboardService {

    private AdbDAO adbDAO;

    public AdashboardService(){
        adbDAO = new AdbDAOImpl();
    }

    public ArrayList<String> getDashboardDetails(int uid) throws SQLException, ClassNotFoundException {
        return adbDAO.getDetails(uid);
    }


}
