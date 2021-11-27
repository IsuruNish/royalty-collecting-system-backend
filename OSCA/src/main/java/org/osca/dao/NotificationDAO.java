package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationDAO {

    public ArrayList<ArrayList<String>> getAllNotificationsFromUID(int uid) throws SQLException, ClassNotFoundException;
//    public ArrayList<ArrayList<String>> getAllREADNotificationsFromUID(int uid) throws SQLException, ClassNotFoundException;
//    public ArrayList<ArrayList<String>> getAllNotificationsFromUtype(int utype) throws SQLException, ClassNotFoundException;
//    public ArrayList<ArrayList<String>> getAllREADNotificationsFromUtype(int utype) throws SQLException, ClassNotFoundException;


    public boolean setNotificationForLicenseRequest(String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForLicenseAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForLicenseDenied(int uid, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongRegRequest(int ut, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongRegAccepted(int utype, String msg, int stage) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongRegDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongOwnRequest(int uid, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongOwnAccepted(int utype, String msg, int stage) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongOwnDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongDelRequest(int uid, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongDelAccepted(int utype, String msg, int stage) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongDelDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

//
    public Boolean deleteNotificationByID(int nid) throws SQLException, ClassNotFoundException;
    public Boolean readAllNotifications(ArrayList<String > ids) throws SQLException, ClassNotFoundException;
    public int newNotificationNumberUID(int uid) throws SQLException, ClassNotFoundException;
//    public int newNotificationNumberUTYPE(int utype) throws SQLException, ClassNotFoundException;
}
