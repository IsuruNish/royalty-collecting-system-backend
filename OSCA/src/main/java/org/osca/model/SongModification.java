package org.osca.model;

import java.util.ArrayList;

public class SongModification {
    ArrayList<ArrayList<String>> songs;

    int tempSongID;
    int songID;
    int version;
    int publishedYear;
    String songName;

    String fname;
    String DPpath;
    int ut;

    public SongModification(ArrayList<ArrayList<String>> songs, String fname, String DPpath, int ut) {
        this.songs = songs;
        this.fname = fname;
        this.DPpath = DPpath;
        this.ut = ut;
    }

    @Override
    public String toString() {
        return "SongModification{" +
                "songs=" + songs +
                ", tempSongID=" + tempSongID +
                ", songID=" + songID +
                ", version=" + version +
                ", publishedYear=" + publishedYear +
                ", songName='" + songName + '\'' +
                ", fname='" + fname + '\'' +
                ", DPpath='" + DPpath + '\'' +
                ", ut=" + ut +
                '}';
    }

    public ArrayList<ArrayList<String>> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<ArrayList<String>> songs) {
        this.songs = songs;
    }

    public int getTempSongID() {
        return tempSongID;
    }

    public void setTempSongID(int tempSongID) {
        this.tempSongID = tempSongID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
}
