package org.osca.dao;

import org.osca.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SongRegistrationDAO {
    public ArrayList<ArrayList<String>> getMembers() throws SQLException, ClassNotFoundException;
    public Boolean storeSong(int uid, int ut, Songs song, String link) throws SQLException, ClassNotFoundException;
    public int getID(String link) throws SQLException, ClassNotFoundException;


    public boolean addMemSingers(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;
    public boolean addMemComposers(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;
    public boolean addMemWritters(int songID, List<String> names, String status)throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> checkNoneMembers()throws SQLException, ClassNotFoundException;
    public boolean addNoneMemToSystem(String fname, String lname)throws SQLException, ClassNotFoundException;
    public int getIDmem(String fname, String lname)throws SQLException, ClassNotFoundException;
    public String makeURLDownloadable(String url)throws SQLException, ClassNotFoundException;


    }
