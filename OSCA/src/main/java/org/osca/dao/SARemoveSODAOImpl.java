package org.osca.dao;

import org.osca.database.DBConnection;
import org.osca.model.ShowOrganizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SARemoveSODAOImpl implements SARemoveSODAO {
    public ArrayList<ShowOrganizer> getShowOrganizers() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getObj().getConnection();
        String q = "SELECT user_id, first_name, last_name, email, phone_number FROM basic_users WHERE delete_status = 0 ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        while(resultSet.next()) {
            if (resultSet != null) {
                users.add(new ShowOrganizer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        }

        return users;
    }

    @Override
    public boolean delShowOrganizers(ShowOrganizer so, String fname, String lname, String email, int uid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getObj().getConnection();
        int empid = 0;

        String q1 = "SELECT emp_id FROM officials WHERE email=? AND first_name=? AND last_name=? AND user_type = ? ;";
        PreparedStatement stmt = connection.prepareStatement(q1);

        stmt.setString(1,email);
        stmt.setString(2, fname);
        stmt.setString(3,lname);
        stmt.setInt(4,uid);

        ResultSet resultSet = stmt.executeQuery();
        if(resultSet.next()){
            empid = resultSet.getInt(1);
        }

        String q2 = "UPDATE basic_users SET delete_status = 1, deleted_by = ?, deleted_on = CURRENT_DATE WHERE first_name = ? AND last_name = ? AND email = ? AND phone_number = ? ;";
        stmt = connection.prepareStatement(q2);

        stmt.setInt(1,empid);
        stmt.setString(2,so.getFname());
        stmt.setString(3,so.getLname());
        stmt.setString(4,so.getEmail());
        stmt.setString(5,so.getPhone());

        return stmt.executeUpdate() > 0;
    }
}
