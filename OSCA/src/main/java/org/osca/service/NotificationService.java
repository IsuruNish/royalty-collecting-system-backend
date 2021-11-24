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

    public ArrayList<ArrayList<String>> getNotificationsUtype(int utype) throws SQLException, ClassNotFoundException {
        return obj.getAllNotificationsFromUtype(utype);
    }

//    public ArrayList<ArrayList<String>> getREADNotificationsUID(int uid) throws SQLException, ClassNotFoundException {
//        return obj.getAllREADNotificationsFromUID(uid);
//    }
//
//    public ArrayList<ArrayList<String>> getREADNotificationsUtype(int utype) throws SQLException, ClassNotFoundException {
//        return obj.getAllREADNotificationsFromUtype(utype);
//    }


    public Boolean setNotificationLicenseRequest(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForLicenseRequest(utype,msg);
    }
    public Boolean setNotificationLicenseAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForLicenseAcceptedOrDenied(utype,msg);
    }


    public Boolean setNotificationSongRegRequest(int uid,int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongRegRequest(uid,utype,msg);
    }
    public Boolean setNotificationSongRegAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongRegAcceptedOrDenied(utype,msg);
    }


    public Boolean setNotificationSongOwnRequest(int uid, int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongOwnRequest(uid, utype,msg);
    }
    public Boolean setNotificationSongOwnAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongOwnAcceptedOrDenied(utype,msg);
    }


    public Boolean setNotificationSongDelRequest(int uid,int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongDelRequest(uid,utype,msg);
    }
    public Boolean setNotificationSongDelAcceptedOrDenied(int utype, String msg) throws SQLException, ClassNotFoundException {
        return obj.setNotificationForSongDelAcceptedOrDenied(utype,msg);
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

    public int newNotificationNumberUsingUTYPE(int utype) throws SQLException, ClassNotFoundException {
        return obj.newNotificationNumberUTYPE(utype);
    }
}
