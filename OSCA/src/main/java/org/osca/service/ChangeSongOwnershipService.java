package org.osca.service;

import org.osca.dao.ChangeSongOwnershipDAO;
import org.osca.dao.ChangeSongOwnershipDAOImpl;
import org.osca.model.ForgetPassword;
import org.osca.model.SongOwnerhip;
import org.osca.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeSongOwnershipService {
    private ChangeSongOwnershipDAO obj;

    public ChangeSongOwnershipService(){
        obj = new ChangeSongOwnershipDAOImpl();
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

    public ArrayList<String> getAllNoneMembersInSystem() throws SQLException, ClassNotFoundException {
        return obj.getAllNoneMembers();
    }






    public Boolean storeSongDetails(int uid, int ut, SongOwnerhip song, String url, int currentID) throws SQLException, ClassNotFoundException {
        return obj.storeSong(uid,ut,song,url, currentID);
    }

    public int getTempSongID(String url) throws SQLException, ClassNotFoundException {
        return obj.getID(url);
    }

    public int getTempSongID(int id) throws SQLException, ClassNotFoundException {
        return obj.getTempID(id);
    }

    //need to check//
    //need to check//
    //need to check//


    public int getMemberID(String fname, String lname) throws SQLException, ClassNotFoundException {
        return obj.getIDmem(fname, lname);
    }

    //need to check//
    //need to check//

    public Boolean addMemberSingers(int songID, List<String> names, String status) throws SQLException, ClassNotFoundException {
        return obj.addMemSingers(songID, names, status);
    }

    public Boolean addMemberComposers(int songID, List<String> names, String status) throws SQLException, ClassNotFoundException {
        return obj.addMemComposers(songID, names, status);
    }
    public Boolean addMemberWritters(int songID, List<String> names,String status) throws SQLException, ClassNotFoundException {
        return obj.addMemWritters(songID, names, status);
    }

    public ArrayList<ArrayList<String>> checkNoneMemberInDB() throws SQLException, ClassNotFoundException {
        return obj.checkNoneMembers();
    }

    public Boolean addNoneMem(String fname, String lname) throws SQLException, ClassNotFoundException {
        return obj.addNoneMemToSystem(fname, lname);
    }



}
