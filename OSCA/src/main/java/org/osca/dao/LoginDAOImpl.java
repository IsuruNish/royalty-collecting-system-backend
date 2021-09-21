package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.UserLoginModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO{

    public UserLoginModel  login(UserLoginModel userLoginModel) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT id,username,userType FROM users WHERE email=? AND password=? ;");
        stmt.setString(1,userLoginModel.getEmail());
        stmt.setString(2, userLoginModel.getPassword());

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            userLoginModel.setId(resultSet.getInt(1));
            userLoginModel.setUsername(resultSet.getString(2));
            userLoginModel.setUserType(resultSet.getInt(3));
            return userLoginModel;
        } else {
            return null;
        }
    }

}
