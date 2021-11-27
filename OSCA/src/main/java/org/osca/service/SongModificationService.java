package org.osca.service;

import org.osca.dao.SongModificationDAO;
import org.osca.dao.SongModificationDAOImpl;
import org.osca.model.SongModification;

import java.sql.SQLException;
import java.util.ArrayList;

public class SongModificationService {
    private SongModificationDAO obj;

    public SongModificationService(){
        obj = new SongModificationDAOImpl();
    }

    public ArrayList<ArrayList<String>> getSongDetails() throws SQLException, ClassNotFoundException {
        return obj.getSongs();
    }


    public boolean sendSongDeleteRequest(int ut, SongModification song,int uid) throws SQLException, ClassNotFoundException {
        return obj.sendSongDeleteReq(ut, song, uid);
    }
}
