package org.osca.dao;

import org.osca.model.User;

import java.util.ArrayList;

public interface UserDAO {
//    public String getAllCustomers(int id)throws SQLException,ClassNotFoundException;
    public ArrayList<User>getAllUsers();
}
