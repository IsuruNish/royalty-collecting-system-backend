package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAllUsers() {

        ArrayList<User>users=new ArrayList<>();

        ResultSet resultSet= null;
        try {
            resultSet = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                 users.add(new User(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }
        return users;
    }
}
