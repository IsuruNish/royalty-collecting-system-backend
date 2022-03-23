package org.osca.dao;

import org.osca.model.License;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ApplyLicenseDAO {
    public ArrayList<ArrayList<Integer>> getSongIDs() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getSongNames(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<Integer> getSongYears(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getSingersFirstName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getSingersLastName(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException;


    public boolean setConcertClose(License license, int uid, double commison, double totFee, double feeWithNoCommison,int songsNo) throws SQLException, ClassNotFoundException;
    public boolean setConcertOpen(License license, int uid,double fee) throws SQLException, ClassNotFoundException;
    public boolean setConcertSongsClose(License license, int uid) throws SQLException, ClassNotFoundException;


    public double getLicenseCommision() throws SQLException, ClassNotFoundException;


    public double getTotFee(int id) throws SQLException, ClassNotFoundException;
    public int getConcertIDClose(License license, int uid) throws SQLException, ClassNotFoundException;
    public int getConcertIDOpen(License license, int uid) throws SQLException, ClassNotFoundException;


    public boolean setSlipPayment(int concertID, String url) throws SQLException, ClassNotFoundException, IOException, MessagingException;
    public boolean setSlipPayment(int concertID) throws SQLException, ClassNotFoundException, IOException, MessagingException;

    }
