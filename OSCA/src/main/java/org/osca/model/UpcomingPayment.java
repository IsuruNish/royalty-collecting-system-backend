package org.osca.model;

import java.util.ArrayList;

public class UpcomingPayment {
    String fname;
    String DPpath;
    int ut;
    int typeOfreq;

    int concertID;
    int userID;
    int memberID;
    int songID;

    ArrayList<ArrayList<String>> license;
    ArrayList<ArrayList<String>> music;
    ArrayList<String> data;


    public UpcomingPayment() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDPpath() {
        return DPpath;
    }

    public void setDPpath(String DPpath) {
        this.DPpath = DPpath;
    }

    public int getUt() {
        return ut;
    }

    public void setUt(int ut) {
        this.ut = ut;
    }

    public ArrayList<ArrayList<String>> getLicense() {
        return license;
    }

    public void setLicense(ArrayList<ArrayList<String>> license) {
        this.license = license;
    }

    public int getTypeOfreq() {
        return typeOfreq;
    }

    public void setTypeOfreq(int typeOfreq) {
        this.typeOfreq = typeOfreq;
    }

    public int getConcertID() {
        return concertID;
    }

    public void setConcertID(int concertID) {
        this.concertID = concertID;
    }

    public ArrayList<ArrayList<String>> getMusic() {
        return music;
    }

    public void setMusic(ArrayList<ArrayList<String>> music) {
        this.music = music;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    @Override
    public String toString() {
        return "UpcomingPayment{" +
                "fname='" + fname + '\'' +
                ", DPpath='" + DPpath + '\'' +
                ", ut=" + ut +
                ", typeOfreq=" + typeOfreq +
                ", concertID=" + concertID +
                ", userID=" + userID +
                ", memberID=" + memberID +
                ", songID=" + songID +
                ", license=" + license +
                ", music=" + music +
                ", data=" + data +
                '}';
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
