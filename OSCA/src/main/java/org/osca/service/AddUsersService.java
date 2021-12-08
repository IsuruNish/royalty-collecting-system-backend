package org.osca.service;

import org.osca.dao.AddUsersDAO;
import org.osca.dao.AddUsersDAOImpl;
import org.osca.model.AAddUsers;
import org.osca.model.MemberDashboard;

import java.sql.SQLException;

public class AddUsersService {

    private AddUsersDAO userDAO;

    public AddUsersService(){
        userDAO = new AddUsersDAOImpl();
    }

    public boolean addOfficials(AAddUsers user, int uid, String emp) throws SQLException, ClassNotFoundException {
        return userDAO.addEmptoSystem(user,uid, emp);
    }

    public boolean addMembers(MemberDashboard user,int uid) throws SQLException, ClassNotFoundException {
        return userDAO.addMtoSystem(user,uid);
    }

    public boolean checkMemberStatus(MemberDashboard user) throws SQLException, ClassNotFoundException {
        return userDAO.checkMember(user);
    }

    public boolean changeNonmemberStatus(MemberDashboard user) throws SQLException, ClassNotFoundException {
        return userDAO.changeNonMemberToMember(user);
    }

    public boolean validateEmail(AAddUsers user) throws SQLException, ClassNotFoundException {
        return userDAO.checkEmail(user);
    }

    public int checkMemberStatus2(MemberDashboard user) throws SQLException, ClassNotFoundException {
        return userDAO.checkMember2(user);
    }

    public boolean changeNonmemberStatus2(MemberDashboard user, int uid, int madeID) throws SQLException, ClassNotFoundException {
        return userDAO.changeNonMemberToMember2(user, uid, madeID);
    }
}
