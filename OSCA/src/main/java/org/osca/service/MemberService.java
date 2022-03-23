package org.osca.service;

import org.osca.dao.MemdbDAO;
import org.osca.dao.MemdbDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {
    private MemdbDAO memdbDAO;

    public MemberService(){
        memdbDAO = new MemdbDAOImpl();
    }

    public ArrayList<String> getDashboardDetails(int uid) throws SQLException, ClassNotFoundException {
        return memdbDAO.getDetails(uid);
    }
}
