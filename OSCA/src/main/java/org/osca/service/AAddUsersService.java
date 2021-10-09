package org.osca.service;

import org.osca.dao.AAddUsersDAO;
import org.osca.dao.AAddUsersDAOImpl;
import org.osca.dao.signupDAO;
import org.osca.dao.signupDAOimp;
import org.osca.model.AAddUsers;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public class AAddUsersService {

    private AAddUsersDAO userDAO;

    public AAddUsersService(){
        userDAO = new AAddUsersDAOImpl();
    }

    public boolean addOscaOfficials(AAddUsers user,int uid) throws SQLException, ClassNotFoundException {
        return userDAO.addUsers(user,uid);
    }
}
