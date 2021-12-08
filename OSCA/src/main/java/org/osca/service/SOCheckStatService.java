package org.osca.service;

import org.osca.dao.SAdbDAO;
import org.osca.dao.SAdbDAOImpl;
import org.osca.dao.SOCheckStatDAO;
import org.osca.dao.SOCheckStatDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class SOCheckStatService {
    private SOCheckStatDAO soCheckStatDAO;

    public SOCheckStatService(){
        soCheckStatDAO = new SOCheckStatDAOImpl();
    }

    public ArrayList<ArrayList<Double>> getPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return soCheckStatDAO.getDetails(uid);
    }

    public ArrayList<ArrayList<Double>> getYearPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return soCheckStatDAO.getYearDetails(uid);
    }

}
