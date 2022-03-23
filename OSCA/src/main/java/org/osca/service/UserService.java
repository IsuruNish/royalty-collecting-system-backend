package org.osca.service;

import org.osca.dao.UserDAO;
import org.osca.dao.UserDAOImpl;
import org.osca.model.User;

import java.util.List;



public class UserService {
    private UserDAO userDAO;
    public UserService(){

        userDAO=new UserDAOImpl();
    }
    public List<User> getUsers(){

         return userDAO.getAllUsers();


    }
}