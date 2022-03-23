package org.osca.dao;

import org.osca.model.people;

import java.sql.SQLException;
import java.util.ArrayList;

public interface peopleDAO {
    public ArrayList<people>getAllPeople() throws SQLException, ClassNotFoundException;

    public boolean addPeople(people ppl) throws SQLException, ClassNotFoundException;
}

