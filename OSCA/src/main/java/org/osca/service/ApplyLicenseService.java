package org.osca.service;


import org.osca.dao.ApplyLicenseDAO;
import org.osca.dao.ApplyLicenseDAOImpl;
import org.osca.model.License;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplyLicenseService {
    private ApplyLicenseDAO obj;

    public ApplyLicenseService(){
        obj = new ApplyLicenseDAOImpl();
    }

    public ArrayList<ArrayList<Integer>> getIDsSongs() throws SQLException, ClassNotFoundException {
        return obj.getSongIDs();
    }

    public ArrayList<String> getSongs(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException {
        return obj.getSongNames(tempSongID);
    }

    public ArrayList<Integer> getYears(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException {
        return obj.getSongYears(tempSongID);
    }

    public ArrayList<ArrayList<String>> getFirstNames(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException {
        return obj.getSingersFirstName(tempSongID);
    }

    public ArrayList<ArrayList<String>>  getlastNames(ArrayList<Integer> tempSongID) throws SQLException, ClassNotFoundException {
        return obj.getSingersLastName(tempSongID);
    }

    public boolean setCloseConcertInfo(License license, int uid, double commison, double totFee, double feeWithNoCommison,int songsNo) throws SQLException, ClassNotFoundException {
        return obj.setConcertClose(license, uid,commison,totFee,feeWithNoCommison, songsNo);
    }

    public boolean setCloseConcertSongInfo(License license,int uid) throws SQLException, ClassNotFoundException {
        return obj.setConcertSongsClose(license, uid);
    }

    public boolean setOpenConcertInfo(License license,int uid, double fee) throws SQLException, ClassNotFoundException {
        return obj.setConcertOpen(license, uid, fee);
    }

    public double getLicenseCommisonPercentage() throws SQLException, ClassNotFoundException {
        return obj.getLicenseCommision();
    }

    public double getTotalFee(int id) throws SQLException, ClassNotFoundException {
        return obj.getTotFee(id);
    }

    public int getCloseConcertID(License license, int uid) throws SQLException, ClassNotFoundException{
        return obj.getConcertIDClose(license, uid);
    }

    public int getOpenConcertID(License license, int uid) throws SQLException, ClassNotFoundException{
        return obj.getConcertIDOpen(license, uid);
    }

    public boolean setPaymentSlip(int concertID, String url) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        return obj.setSlipPayment(concertID, url);
    }

    public boolean setPaymentSuccessful(int concertID) throws SQLException, ClassNotFoundException, IOException, MessagingException {
        return obj.setSlipPayment(concertID);
    }
}

