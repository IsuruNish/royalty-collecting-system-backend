package org.osca.dao;

import org.osca.model.AAddUsers;
import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public interface AAddUsersDAO {
    public boolean addUsers(AAddUsers user,int uid) throws SQLException, ClassNotFoundException;
}
