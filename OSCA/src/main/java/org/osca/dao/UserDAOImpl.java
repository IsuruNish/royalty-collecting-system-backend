package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public boolean isRegisteredUser(String email) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT userID FROM j4f9qe_user WHERE email=?");
        stmt.setString(1,email);

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean resetPassword(String email,String password) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("UPDATE j4f9qe_user SET password=? WHERE email=? ");
        stmt.setString(1,password);
        stmt.setString(2,email);

        int reset=stmt.executeUpdate();
        if (reset>0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeUser(int empID) throws SQLException, ClassNotFoundException {
        Statement stmt = DBConnection.getConnection().createStatement();
        String query = "DELETE FROM j4f9qe_user WHERE userID IN(SELECT userID FROM j4f9qe_employee WHERE employeeID="+empID+")";
        int deletedRows = stmt.executeUpdate(query);

        if(deletedRows==1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public ArrayList<String> getUserEmails(int userType) throws SQLException, ClassNotFoundException {
        ArrayList<String> email = new ArrayList<>();

        PreparedStatement stmt = DBConnection.getConnection().prepareStatement("SELECT email FROM j4f9qe_user WHERE userType=?");
        stmt.setInt(1, userType);

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            email.add(resultSet.getString(1));
        }

        return email;

    }
}
