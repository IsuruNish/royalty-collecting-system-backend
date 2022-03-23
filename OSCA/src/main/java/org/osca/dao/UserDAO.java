package org.osca.dao;

import org.osca.model.User;

import java.sql.SQLException;

import java.util.ArrayList;

public interface UserDAO {
//    public String getAllCustomers(int id)throws SQLException,ClassNotFoundException;
    public ArrayList<User>getAllUsers();


    public boolean isRegisteredUser(String email) throws SQLException, ClassNotFoundException;

    public boolean resetPassword(String email,String password) throws SQLException, ClassNotFoundException;

    public boolean removeUser(int empID) throws SQLException, ClassNotFoundException;

    //    get Emails for userTypes
    public ArrayList<String> getUserEmails(int userType) throws SQLException, ClassNotFoundException;

}
