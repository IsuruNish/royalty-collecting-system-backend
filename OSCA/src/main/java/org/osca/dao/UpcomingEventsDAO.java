package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UpcomingEventsDAO {

    public ArrayList<ArrayList<String>> getSOUpcomingEvents(int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getMemUpcomingEvents(int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getEmpUpcomingEvents() throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getAllSOUpcomingEvents(int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getSongs(int concertID) throws SQLException, ClassNotFoundException;
}
