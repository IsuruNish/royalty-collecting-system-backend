package org.osca.service;

import org.osca.dao.MCheckStatDAO;
import org.osca.dao.MCheckStatDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class MCheckStatService {
    private MCheckStatDAO mCheckStatDAO;

    public MCheckStatService(){
        mCheckStatDAO = new MCheckStatDAOImpl();
    }

    public ArrayList<ArrayList<Double>> getPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return mCheckStatDAO.getDetails(uid);
    }

    public ArrayList<ArrayList<Double>> getYearPaymentHistory(int uid) throws SQLException, ClassNotFoundException {
        return mCheckStatDAO.getYearDetails(uid);
    }
}
