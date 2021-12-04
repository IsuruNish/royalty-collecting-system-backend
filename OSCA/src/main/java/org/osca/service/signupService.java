package org.osca.service;

import org.osca.dao.signupDAO;
import org.osca.dao.signupDAOimp;
import org.osca.model.ShowOrganizer;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class signupService {
    private signupDAO userDAO;

    public signupService(){
        userDAO = new signupDAOimp();
    }

    public boolean addShowOrganizers(ShowOrganizer ppl) throws SQLException, ClassNotFoundException, MessagingException {
        return userDAO.addSOs(ppl);
    }

    public int getUid(ShowOrganizer ppl) throws SQLException, ClassNotFoundException {
        return userDAO.getBasicUserID(ppl);
    }

    public boolean verifyEmailForSO(int uid) throws SQLException, ClassNotFoundException {
        return userDAO.verifyEmailSO(uid);
    }

    public boolean verifyEmailForMem(int uid) throws SQLException, ClassNotFoundException {
        return userDAO.verifyEmailMem(uid);
    }

    public boolean verifyEmailForEmp(int uid) throws SQLException, ClassNotFoundException {
        return userDAO.verifyEmailEmp(uid);
    }
}
