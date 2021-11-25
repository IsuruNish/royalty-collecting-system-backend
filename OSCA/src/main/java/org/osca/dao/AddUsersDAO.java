package org.osca.dao;

import org.osca.model.AAddUsers;
import org.osca.model.MemberDashboard;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public interface AddUsersDAO {
    public boolean addEmptoSystem(AAddUsers user,int uid, String emp) throws SQLException, ClassNotFoundException;
    public boolean addMtoSystem(MemberDashboard user, int uid) throws SQLException, ClassNotFoundException;
    public boolean checkMember(MemberDashboard user) throws SQLException, ClassNotFoundException;
    public boolean changeNonMemberToMember(MemberDashboard user) throws SQLException, ClassNotFoundException;
    public boolean checkEmail(AAddUsers user) throws SQLException, ClassNotFoundException;
    public int checkMember2(MemberDashboard user) throws SQLException, ClassNotFoundException;
    public boolean changeNonMemberToMember2(MemberDashboard user, int uid, int madeID) throws SQLException, ClassNotFoundException;
}
