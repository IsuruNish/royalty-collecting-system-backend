package org.osca.dao;

import org.osca.database.DBcon;
import org.osca.model.people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class peopleDAOimp implements peopleDAO{

    public ArrayList<people> getAllPeople() throws SQLException, ClassNotFoundException {
        ArrayList<people> ppl = new ArrayList<>();

        Connection connection = DBcon.getObj().getConnecton();
        String query = "SELECT * FROM people";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            if(resultSet != null){
                ppl.add(new people(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }
        }
        return ppl;
    }
}
