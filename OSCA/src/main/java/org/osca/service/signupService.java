package org.osca.service;

import org.osca.dao.signupDAO;
import org.osca.dao.signupDAOimp;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public class signupService {
    private signupDAO userDAO;

    public signupService(){
        userDAO = new signupDAOimp();
    }

    public boolean addShowOrganizers(ShowOrganizer ppl) throws SQLException, ClassNotFoundException {
        return userDAO.addSOs(ppl);
    }

    public int getUid(ShowOrganizer ppl) throws SQLException, ClassNotFoundException {
        return userDAO.getBasicUserID(ppl);
    }
}
