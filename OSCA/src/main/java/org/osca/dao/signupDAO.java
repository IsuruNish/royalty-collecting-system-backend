package org.osca.dao;

import org.osca.model.ShowOrganizer;

import java.sql.SQLException;

public interface signupDAO {
//    public ArrayList<Show_Organizer>getAllPeople() throws SQLException, ClassNotFoundException;

    public boolean addSOs(ShowOrganizer user) throws SQLException, ClassNotFoundException;

    public int getBasicUserID(ShowOrganizer user) throws SQLException, ClassNotFoundException;


}

