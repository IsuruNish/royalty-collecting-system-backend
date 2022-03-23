package org.osca.dao;

import org.osca.model.SongOwnerhip;
import org.osca.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ChangeSongOwnershipDAO {
    public ArrayList<String> getAllSongDetails(int id) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getAllSingers(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllComposers(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllWriters(int id) throws SQLException, ClassNotFoundException;




    public Boolean storeSong(int uid, int ut, SongOwnerhip song, String link, int currentID) throws SQLException, ClassNotFoundException;
    public int getID(String link) throws SQLException, ClassNotFoundException;
    public int getTempID(int id) throws SQLException, ClassNotFoundException;


    public boolean addMemSingers(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;
    public boolean addMemComposers(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;
    public boolean addMemWritters(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> checkNoneMembers()throws SQLException, ClassNotFoundException;
    public boolean addNoneMemToSystem(String fname, String lname)throws SQLException, ClassNotFoundException;
    public int getIDmem(String fname, String lname)throws SQLException, ClassNotFoundException;

}
