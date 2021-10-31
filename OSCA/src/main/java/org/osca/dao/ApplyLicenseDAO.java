package org.osca.dao;

import org.osca.model.License;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ApplyLicenseDAO {
    public ArrayList<ArrayList<Integer>> getSongIDs() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getSongNames(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<Integer> getSongYears(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getSingersFirstName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getSingersLastName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;


    public boolean setConcertClose(License license, int uid) throws SQLException, ClassNotFoundException;
    public boolean setConcertOpen(License license, int uid) throws SQLException, ClassNotFoundException;
    public boolean setConcertSongsClose(License license, int uid) throws SQLException, ClassNotFoundException;



    }
