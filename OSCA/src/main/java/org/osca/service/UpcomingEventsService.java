package org.osca.service;

import org.osca.dao.UpcomingEventsDAO;
import org.osca.dao.UpcomingEventsDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpcomingEventsService {
    private UpcomingEventsDAO obj;

    public UpcomingEventsService(){
        obj = new UpcomingEventsDAOImpl();
    }

    public ArrayList<ArrayList<String>> getUpcomingEventsSO(int uid) throws SQLException, ClassNotFoundException {
        return obj.getSOUpcomingEvents(uid);
    }

    public ArrayList<ArrayList<String>> getUpcomingEventsMem(int uid) throws SQLException, ClassNotFoundException {
        return obj.getMemUpcomingEvents(uid);
    }

    public ArrayList<ArrayList<String>> getUpcomingEventsEmp() throws SQLException, ClassNotFoundException {
        return obj.getEmpUpcomingEvents();
    }

    public ArrayList<String> getSongsForconcert(int concertID) throws SQLException, ClassNotFoundException {
        return obj.getSongs(concertID);
    }
}
