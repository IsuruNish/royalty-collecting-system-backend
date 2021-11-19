package org.osca.model;

import java.util.ArrayList;
import java.util.List;

public class License {
    private ArrayList<Integer> songTempIds = new ArrayList<>();
    private ArrayList<Integer> songIds = new ArrayList<>();
    private ArrayList<String> songNames = new ArrayList<>();
    private ArrayList<Integer> songYears = new ArrayList<>();
    private ArrayList<ArrayList<String>> fNames= new ArrayList<>();
    private ArrayList<ArrayList<String>> lNames= new ArrayList<>();
    private int requestType;
    private int concertID;
    private double totalFee;

    private String concertName;
    private String venue;
    private String date;

    private int utype;
    private int uid;
    private String fname;
    private String DPpath;

    public License() {
    }


    public ArrayList<Integer> getSongTempIds() {
        return songTempIds;
    }

    public void setSongTempIds(ArrayList<Integer> songTempIds) {
        this.songTempIds = songTempIds;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public ArrayList<Integer> getSongIds() {
        return songIds;
    }

    public void setSongIds(ArrayList<Integer> songIds) {
        this.songIds = songIds;
    }

    public ArrayList<String> getSongNames() {
        return songNames;
    }

    public void setSongNames(ArrayList<String> songNames) {
        this.songNames = songNames;
    }

    public ArrayList<Integer> getSongYears() {
        return songYears;
    }

    public void setSongYears(ArrayList<Integer> songYears) {
        this.songYears = songYears;
    }

    public ArrayList<ArrayList<String>> getfNames() {
        return fNames;
    }

    public void setfNames(ArrayList<ArrayList<String>> fNames) {
        this.fNames = fNames;
    }

    public ArrayList<ArrayList<String>> getlNames() {
        return lNames;
    }

    public void setlNames(ArrayList<ArrayList<String>> lNames) {
        this.lNames = lNames;
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

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public int getConcertID() {
        return concertID;
    }

    public void setConcertID(int concertID) {
        this.concertID = concertID;
    }

    @Override
    public String toString() {
        return "License{" +
                "songTempIds=" + songTempIds +
                ", songIds=" + songIds +
                ", songNames=" + songNames +
                ", songYears=" + songYears +
                ", fNames=" + fNames +
                ", lNames=" + lNames +
                ", requestType=" + requestType +
                ", concertID=" + concertID +
                ", totalFee=" + totalFee +
                ", concertName='" + concertName + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", utype=" + utype +
                ", uid=" + uid +
                ", fname='" + fname + '\'' +
                ", DPpath='" + DPpath + '\'' +
                '}';
    }
}
