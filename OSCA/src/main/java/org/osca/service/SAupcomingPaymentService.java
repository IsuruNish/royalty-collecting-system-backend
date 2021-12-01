package org.osca.service;

import org.osca.dao.SAupcomingPaymentDAO;
import org.osca.dao.SAupcomingPaymentDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class SAupcomingPaymentService {
    private SAupcomingPaymentDAO sadbDAO;

    public SAupcomingPaymentService(){
        sadbDAO = new SAupcomingPaymentDAOImpl();
    }

    public ArrayList<ArrayList<String>> getCancelledLicense() throws SQLException, ClassNotFoundException {
        return sadbDAO.getCancelledLicenses();
    }

    public ArrayList<ArrayList<String>> getMemberPayments() throws SQLException, ClassNotFoundException {
        return sadbDAO.getMemPayments();
    }

    public Boolean setPaymentShowOrganizer(int uid, int concertID) throws SQLException, ClassNotFoundException {
        return sadbDAO.setPaymentSO(uid, concertID);
    }

    public Boolean setPaymentMember(int uid, int concertID, int songID) throws SQLException, ClassNotFoundException {
        return sadbDAO.setPaymentMem(uid, concertID, songID);
    }


    public ArrayList<String> getShowOrganizerDetails(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getSoDetails(uid);
    }

    public ArrayList<String> getMemberDetails(int uid) throws SQLException, ClassNotFoundException {
        return sadbDAO.getMemDetails(uid);
    }
}
