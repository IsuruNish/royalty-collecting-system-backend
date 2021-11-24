package org.osca.model;

import java.util.ArrayList;

public class Notification {
    int uid;
    int ut;
    int notificationID;
    int requestType;
    int numberOfNotifications;
    String fname;
    String dpPath;
    ArrayList<ArrayList<String>> msg;
    ArrayList<String> unreadIDs;

    public Notification() {
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUt() {
        return ut;
    }

    public void setUt(int ut) {
        this.ut = ut;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDpPath() {
        return dpPath;
    }

    public void setDpPath(String dpPath) {
        this.dpPath = dpPath;
    }

    public ArrayList<ArrayList<String>> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<ArrayList<String>> msg) {
        this.msg = msg;
    }

    public ArrayList<String> getUnreadIDs() {
        return unreadIDs;
    }

    public void setUnreadIDs(ArrayList<String> unreadIDs) {
        this.unreadIDs = unreadIDs;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getNumberOfNotifications() {
        return numberOfNotifications;
    }

    public void setNumberOfNotifications(int numberOfNotifications) {
        this.numberOfNotifications = numberOfNotifications;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "uid=" + uid +
                ", ut=" + ut +
                ", notificationID=" + notificationID +
                ", requestType=" + requestType +
                ", numberOfNotifications=" + numberOfNotifications +
                ", fname='" + fname + '\'' +
                ", dpPath='" + dpPath + '\'' +
                ", msg=" + msg +
                ", unreadIDs=" + unreadIDs +
                '}';
    }
}
