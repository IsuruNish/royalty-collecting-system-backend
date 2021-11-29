package org.osca.model;

import java.util.ArrayList;

public class UpcomingEvents {
    ArrayList<ArrayList<String>> details;
    ArrayList<String> songs;
    String fname;
    String DPpath;
    int ut;
    int concertID;


    public ArrayList<ArrayList<String>> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<ArrayList<String>> details) {
        this.details = details;
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

    public int getConcertID() {
        return concertID;
    }

    public void setConcertID(int concertID) {
        this.concertID = concertID;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "UpcomingEvents{" +
                "details=" + details +
                ", songs=" + songs +
                ", fname='" + fname + '\'' +
                ", DPpath='" + DPpath + '\'' +
                ", ut=" + ut +
                ", concertID=" + concertID +
                '}';
    }
}
