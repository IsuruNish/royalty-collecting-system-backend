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
        String q = "SELECT first_name, last_name FROM basic_users ORDER BY first_name;";
        PreparedStatement stmt = connection.prepareStatement(q);

        ResultSet resultSet = stmt.executeQuery();

        ArrayList<ShowOrganizer> users = new ArrayList<>();

        if(resultSet.next()){
            users.add(new ShowOrganizer(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }

        return users;
    }
}
