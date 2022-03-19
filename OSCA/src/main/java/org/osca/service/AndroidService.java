package org.osca.service;

import org.osca.dao.AndroidPaymentDAO;
import org.osca.dao.AndroidPaymentDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class AndroidService {

    private AndroidPaymentDAO aDAO;

    public AndroidService(){
        aDAO = new AndroidPaymentDAOImpl();
    }


    public ArrayList<ArrayList<String>> AndroidGetUpcomingPayments(int uid) throws SQLException, ClassNotFoundException{
        return aDAO.getUpcomingPaymentsForAndroid(uid);

    }
    public ArrayList<ArrayList<String>> AndroidGetPastPayments(int uid) throws SQLException, ClassNotFoundException{
        return aDAO.getPastPaymentsForAndroid(uid);

    }


}
