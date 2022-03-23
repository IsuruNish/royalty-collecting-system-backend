package org.osca.dao;

import org.osca.model.SongModification;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongModificationDAO {
    public ArrayList<ArrayList<String>> getSongs() throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Integer>> getSongIDs() throws SQLException, ClassNotFoundException;

    public boolean sendSongDeleteReq(int ut, SongModification song,int uid) throws SQLException, ClassNotFoundException;
}
