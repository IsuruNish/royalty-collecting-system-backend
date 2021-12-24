package org.osca.service;

import org.osca.dao.SOCancelLicenseDAO;
import org.osca.dao.SOCancelLicenseDAOImpl;
import org.osca.model.CancelLicense;
import org.osca.model.ConcertIDModel;
import org.osca.model.SOCancelLicense;


import java.sql.SQLException;
import java.util.ArrayList;

public class SOCancelLicenseService {

    private SOCancelLicenseDAO soCancelLicenseDAO;

    public SOCancelLicenseService(){
        soCancelLicenseDAO=new SOCancelLicenseDAOImpl();
    }

    public boolean getID(ConcertIDModel concertIDModel) throws SQLException, ClassNotFoundException {
        return soCancelLicenseDAO.getIDs(concertIDModel);
//        return loginDAO.getUserID(userLoginModel);
    }

    public ArrayList<ArrayList<String>> getConcertDetails(int uid) throws SQLException, ClassNotFoundException {
        return soCancelLicenseDAO.getDetails(uid);
    }

}
