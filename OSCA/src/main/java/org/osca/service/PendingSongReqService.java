package org.osca.service;

import org.osca.dao.PendingSongReqDAO;
import org.osca.dao.PendingSongReqDAOImpl;
import org.osca.dao.SongModificationDAO;
import org.osca.dao.SongModificationDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class PendingSongReqService {
    private PendingSongReqDAO obj;

    public PendingSongReqService(){
        obj = new PendingSongReqDAOImpl();
    }

    public ArrayList<ArrayList<String>> getPendingReqSongDetails(int ut, int uid) throws SQLException, ClassNotFoundException {
        return obj.getPendingSongs(ut,uid);
    }

    public ArrayList<String> getSongDetails(int id) throws SQLException, ClassNotFoundException {
        return obj.getAllSongDetails(id);
    }

    public ArrayList<ArrayList<String>> getSingers(int id) throws SQLException, ClassNotFoundException {
        return obj.getAllSingers(id);
    }

    public ArrayList<ArrayList<String>> getComposers(int id) throws SQLException, ClassNotFoundException {
        return obj.getAllComposers(id);
    }

    public ArrayList<ArrayList<String>> getWriters(int id) throws SQLException, ClassNotFoundException {
        return obj.getAllWriters(id);
    }

    public boolean cancelRequest(int tid) throws SQLException, ClassNotFoundException {
        return obj.cancelReq(tid);
    }
}
