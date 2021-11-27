package org.osca.model;

import java.util.ArrayList;

public class Requests {
    ArrayList<String> info;
    ArrayList<ArrayList<String>> licenseAppReqs;
    ArrayList<ArrayList<String>> songRegReq;
    ArrayList<ArrayList<String>> songOwnReq;
    ArrayList<ArrayList<String>> songDelReq;

    int licenseID;
    int showOrganizerID;

    String concertName;
    int songID;
    int memberID;
    String songName;

    int reqType;
    int isAccepted;

    public ArrayList<String> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }

    public ArrayList<ArrayList<String>> getLicenseAppReqs() {
        return licenseAppReqs;
    }

    public void setLicenseAppReqs(ArrayList<ArrayList<String>> licenseAppReqs) {
        this.licenseAppReqs = licenseAppReqs;
    }

    public ArrayList<ArrayList<String>> getSongRegReq() {
        return songRegReq;
    }

    public void setSongRegReq(ArrayList<ArrayList<String>> songRegReq) {
        this.songRegReq = songRegReq;
    }

    public ArrayList<ArrayList<String>> getSongOwnReq() {
        return songOwnReq;
    }

    public void setSongOwnReq(ArrayList<ArrayList<String>> songOwnReq) {
        this.songOwnReq = songOwnReq;
    }

    public ArrayList<ArrayList<String>> getSongDelReq() {
        return songDelReq;
    }

    public void setSongDelReq(ArrayList<ArrayList<String>> songDelReq) {
        this.songDelReq = songDelReq;
    }

    public int getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(int licenseID) {
        this.licenseID = licenseID;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public int getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public Requests() {
    }

    public int getShowOrganizerID() {
        return showOrganizerID;
    }

    public void setShowOrganizerID(int showOrganizerID) {
        this.showOrganizerID = showOrganizerID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "info=" + info +
                ", licenseAppReqs=" + licenseAppReqs +
                ", songRegReq=" + songRegReq +
                ", songOwnReq=" + songOwnReq +
                ", songDelReq=" + songDelReq +
                ", licenseID=" + licenseID +
                ", showOrganizerID=" + showOrganizerID +
                ", concertName='" + concertName + '\'' +
                ", songID=" + songID +
                ", memberID=" + memberID +
                ", songName='" + songName + '\'' +
                ", reqType=" + reqType +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
