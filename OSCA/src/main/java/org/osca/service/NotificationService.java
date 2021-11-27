package org.osca.service;

import org.osca.dao.NotificationDAO;
import org.osca.dao.NotificationDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationService {
    private NotificationDAO obj;

    public NotificationService(){
        obj = new NotificationDAOImpl();
    }


    public ArrayList<ArrayList<String>> getNotificationsUID(int uid) throws SQLException, ClassNotFoundException {
        return obj.getAllNotificationsFromUID(uid);
    }

//    public ArrayList<ArrayList<String>> getNotificationsUtype(int utype) throws SQLException, ClassNotFoundException {
//        return obj.getAllNotificationsFromUtype(utype);
//    }


    public Boolean setNotificationLicenseRequest(String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForLicenseRequest(msg);
    }
    public Boolean setNotificationLicenseAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForLicenseAccepted(uid,msg, stage);
    }
    public Boolean setNotificationLicenseDenied(int uid, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForLicenseDenied(uid,msg);
    }

//////////
    public Boolean setNotificationSongRegRequest(int ut,String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongRegRequest(ut, msg);
    }
    public Boolean setNotificationSongRegAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongRegAccepted(uid,msg, stage);
    }
    public Boolean setNotificationSongRegDenied(int uid, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongRegDenied(uid,msg);
    }



    public Boolean setNotificationSongOwnRequest(int uid, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongOwnRequest(uid,msg);
    }
    public Boolean setNotificationSongOwnAccepted(int utype, String msg, int stage) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongOwnAccepted(utype,msg, stage);
    }
    public Boolean setNotificationSongOwnDenied(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongOwnDenied(utype,msg);
    }



    public Boolean setNotificationSongDelRequest(int uid, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongDelRequest(uid,msg);
    }
    public Boolean setNotificationSongDelAccepted(int uid, String msg, int stage) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongDelAccepted(uid,msg, stage);
    }
    public Boolean setNotificationSongDelDenied(int uid, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongDelDenied(uid,msg);
    }




    public Boolean deleteNotification(int nid) throws SQLException, ClassNotFoundException {
        return obj.deleteNotificationByID(nid);
    }

    public Boolean readNotifications(ArrayList<String > ids) throws SQLException, ClassNotFoundException {
        return obj.readAllNotifications(ids);
    }

    public int newNotificationNumberUsingUID(int uid) throws SQLException, ClassNotFoundException {
        return obj.newNotificationNumberUID(uid);
    }
//
//    public int newNotificationNumberUsingUTYPE(int utype) throws SQLException, ClassNotFoundException {
//        return obj.newNotificationNumberUTYPE(utype);
//    }
}
