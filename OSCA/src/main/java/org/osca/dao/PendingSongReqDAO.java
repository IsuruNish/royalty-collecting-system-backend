package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PendingSongReqDAO {
    public ArrayList<ArrayList<String>> getPendingSongs(int ut, int uid) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllSongDetails(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllSingers(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllComposers(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllWriters(int id) throws SQLException, ClassNotFoundException;

    public boolean cancelReq(int tid) throws SQLException, ClassNotFoundException;
}
