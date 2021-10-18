package org.osca.service;

import org.osca.dao.AddUsersDAO;
import org.osca.dao.AddUsersDAOImpl;
import org.osca.model.AAddUsers;

import java.sql.SQLException;

public class AddUsersService {

    private AddUsersDAO userDAO;

    public AddUsersService(){
        userDAO = new AddUsersDAOImpl();
    }

    public boolean addOfficials(AAddUsers user, int uid, String emp) throws SQLException, ClassNotFoundException {
        return userDAO.addEmptoSystem(user,uid, emp);
    }

    public boolean addMembers(AAddUsers user,int uid) throws SQLException, ClassNotFoundException {
        return userDAO.addMtoSystem(user,uid);
    }

    public boolean validateEmail(AAddUsers user) throws SQLException, ClassNotFoundException {
        return userDAO.checkEmail(user);
    }

}
