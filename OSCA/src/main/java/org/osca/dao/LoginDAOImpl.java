package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.UserLoginModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO{

    public UserLoginModel  login(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(
                "SELECT first_name,last_name,email,userType FROM( SELECT first_name,last_name,email,userType FROM officials WHERE email=? AND password=? UNION SELECT first_name,last_name,email,userType FROM members WHERE email=? AND password=? UNION SELECT first_name,last_name,email,userType FROM basic_users WHERE email=? AND password=?) WHERE email=? AND password=?;");

        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            userLoginModel.setFirstName(resultSet.getString(1));
            userLoginModel.setLastName(resultSet.getString(2));
            userLoginModel.setEmail(resultSet.getString(3));
            userLoginModel.setUserType(resultSet.getInt(4));
            return userLoginModel;
        } else {
            return null;
        }
    }

}
