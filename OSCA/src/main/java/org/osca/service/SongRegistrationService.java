package org.osca.service;

import org.osca.dao.SongRegistrationDAO;
import org.osca.dao.SongRegistrationDAOImpl;
import org.osca.model.Songs;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRegistrationService {
    private SongRegistrationDAO memDAO;

    public SongRegistrationService(){
        memDAO = new SongRegistrationDAOImpl();
    }

    public ArrayList<ArrayList<String>> getMemberDetails() throws SQLException, ClassNotFoundException {
        return memDAO.getMembers();
    }

    public Boolean storeSongDetails(int uid, int ut, Songs song, String url) throws SQLException, ClassNotFoundException {
        return memDAO.storeSong(uid,ut,song,url);
    }

    public int getTempSongID(String url) throws SQLException, ClassNotFoundException {
        return memDAO.getID(url);
    }

    public int getMemberID(String fname, String lname) throws SQLException, ClassNotFoundException {
        return memDAO.getIDmem(fname, lname);
    }

    public Boolean addMemberSingers(int songID, List<String> names,String status) throws SQLException, ClassNotFoundException {
        return memDAO.addMemSingers(songID, names, status);
    }
    public Boolean addMemberComposers(int songID, List names,String status) throws SQLException, ClassNotFoundException {
        return memDAO.addMemComposers(songID, names, status);
    }
    public Boolean addMemberWritters(int songID, List names,String status) throws SQLException, ClassNotFoundException {
        return memDAO.addMemWritters(songID, names, status);
    }

    public ArrayList<ArrayList<String>> checkNoneMemberInDB() throws SQLException, ClassNotFoundException {
        return memDAO.checkNoneMembers();
    }

    public Boolean addNoneMem(String fname, String lname) throws SQLException, ClassNotFoundException {
        return memDAO.addNoneMemToSystem(fname, lname);
    }
}
