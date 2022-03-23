package org.osca.service;

import org.osca.dao.OOlicensePaymentDAO;
import org.osca.dao.OOlicensePaymentDAOImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OOlicensePaymentService {
    private OOlicensePaymentDAO obj;

    public OOlicensePaymentService(){
        obj = new OOlicensePaymentDAOImpl();
    }

    public ArrayList<ArrayList<String>> getLicensePaymentData() throws SQLException, ClassNotFoundException {
        return obj.getLicensePaymentInfo();
    }

    public boolean licensePaymentAccepted(int concertID) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        return obj.paymentAccepted(concertID);
    }
    public boolean licensePaymentDenied(int concertID) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        return obj.paymentDeny(concertID);
    }


}
