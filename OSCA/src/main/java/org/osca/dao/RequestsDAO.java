package org.osca.dao;

import org.osca.database.DBConnection;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestsDAO {

    public ArrayList<ArrayList<String>> getLicenseAppReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongRegReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongOwnReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongDelReq(int type) throws SQLException, ClassNotFoundException;



    public Boolean setLicenseReqAccept(int id, int type) throws SQLException, ClassNotFoundException, IOException, MessagingException;
    public Boolean setLicenseReqDeny(int id) throws SQLException, ClassNotFoundException;
    public Boolean newLicense(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongRegReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongRegReqDeny(int id) throws SQLException, ClassNotFoundException;
    public Boolean newSong(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongOwnReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongOwnReqDeny(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongDelReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongDelReqDeny(int id) throws SQLException, ClassNotFoundException;



    public Boolean isCloseConcert(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<Integer> getSongIDsInConcert(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<Integer> getTempSongIDsInConcert(ArrayList<Integer> ids) throws SQLException, ClassNotFoundException;
    public double getTotalFeeForConcert(int id) throws SQLException, ClassNotFoundException;
    public String getDateForConcert(int id) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Integer>> getComposersForConcert(int Tempid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<Integer>> getWrittersForConcert(int Tempid) throws SQLException, ClassNotFoundException;
    public Boolean putIncomingForMembersComposers(int id, double amount, ArrayList<Integer> members, String concertDate, int concertID) throws SQLException, ClassNotFoundException;
    public Boolean putIncomingForMembersWritters(int id, double amount, ArrayList<Integer> members, String concertDate,int concertID) throws SQLException, ClassNotFoundException;

    public boolean allDoneForTheNewNonMember(int uid) throws SQLException, ClassNotFoundException;

}
