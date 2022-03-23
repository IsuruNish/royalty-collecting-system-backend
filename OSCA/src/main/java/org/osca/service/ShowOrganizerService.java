package org.osca.service;


import org.osca.dao.SOdbDAO;
import org.osca.dao.SOdbDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowOrganizerService {
    private SOdbDAO sodbDAO;

    public ShowOrganizerService(){
        sodbDAO = new SOdbDAOImpl();
    }

    public ArrayList<String> getDashboardDetails(int uid) throws SQLException, ClassNotFoundException {
        return sodbDAO.getDetails(uid);
    }
}
