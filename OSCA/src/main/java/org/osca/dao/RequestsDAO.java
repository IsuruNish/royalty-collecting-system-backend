package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RequestsDAO {

    public ArrayList<ArrayList<String>> getLicenseAppReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongRegReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongOwnReq(int type) throws SQLException, ClassNotFoundException;

    public ArrayList<ArrayList<String>> getSongDelReq(int type) throws SQLException, ClassNotFoundException;



    public Boolean setLicenseReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setLicenseReqDeny(int id) throws SQLException, ClassNotFoundException;
    public Boolean newLicense(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongRegReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongRegReqDeny(int id) throws SQLException, ClassNotFoundException;
    public Boolean newSong(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongOwnReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongOwnReqDeny(int id) throws SQLException, ClassNotFoundException;

    public Boolean setSongDelReqAccept(int id, int type) throws SQLException, ClassNotFoundException;
    public Boolean setSongDelReqDeny(int id) throws SQLException, ClassNotFoundException;

    }
