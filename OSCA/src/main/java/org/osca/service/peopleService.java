package org.osca.service;

import org.osca.dao.peopleDAO;
import org.osca.dao.peopleDAOimp;
import org.osca.model.people;

import java.sql.SQLException;
import java.util.ArrayList;

public class peopleService {
    private peopleDAO pplDAO;

    public peopleService(){
        pplDAO = new peopleDAOimp();
    }

    public ArrayList<people>getPeople() throws SQLException, ClassNotFoundException {
        return pplDAO.getAllPeople();
    }

    public boolean addPeople(people ppl) throws SQLException, ClassNotFoundException {
        return pplDAO.addPeople(ppl);
    }
}
