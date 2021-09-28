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

    public ArrayList<String> getDashboardDetails(String fname, String lname, String email) throws SQLException, ClassNotFoundException {
        return sadbDAO.getDetails(fname, lname, email);
    }
}
