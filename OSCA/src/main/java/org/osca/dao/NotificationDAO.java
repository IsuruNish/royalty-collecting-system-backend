package org.osca.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NotificationDAO {

    public ArrayList<ArrayList<String>> getAllNotificationsFromUID(int uid) throws SQLException, ClassNotFoundException;
//    public ArrayList<ArrayList<String>> getAllREADNotificationsFromUID(int uid) throws SQLException, ClassNotFoundException;
    public ArrayList<ArrayList<String>> getAllNotificationsFromUtype(int utype) throws SQLException, ClassNotFoundException;
//    public ArrayList<ArrayList<String>> getAllREADNotificationsFromUtype(int utype) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForLicenseRequest(int utype, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForLicenseAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongRegRequest(int uid,int utype, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongRegAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongOwnRequest(int uid, int utype, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongOwnAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public boolean setNotificationForSongDelRequest(int uid, int utype, String msg) throws SQLException, ClassNotFoundException;
    public boolean setNotificationForSongDelAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException;

    public Boolean deleteNotificationByID(int nid) throws SQLException, ClassNotFoundException;
    public Boolean readAllNotifications(ArrayList<String > ids) throws SQLException, ClassNotFoundException;
    public int newNotificationNumberUID(int uid) throws SQLException, ClassNotFoundException;
    public int newNotificationNumberUTYPE(int utype) throws SQLException, ClassNotFoundException;
}
