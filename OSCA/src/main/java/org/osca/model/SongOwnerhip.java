package org.osca.model;

import java.util.ArrayList;
import java.util.List;

public class SongOwnerhip {
    private List<String> memSingers;
    private List<String> memComposers;
    private List<String> memWritters;
    private List<String> NOmemSingers;
    private List<String> NOmemComposers;
    private List<String> NOmemWritters;

    private List<String> delSinger;
    private List<String> delComposers;
    private List<String> delWritters;

    private List<String> info;
    private int songID;
    private int reqType;

    private ArrayList<ArrayList<String>> POSTsingers;
    private ArrayList<ArrayList<String>> POSTcomposers;
    private ArrayList<ArrayList<String>> POSTwriters;
    private ArrayList<String> POSTinfo;

    private ArrayList<String> allNoneMemberNames;


    @Override
    public String toString() {
        return "SongOwnerhip{" +
                "memSingers=" + memSingers +
                ", memComposers=" + memComposers +
                ", memWritters=" + memWritters +
                ", NOmemSingers=" + NOmemSingers +
                ", NOmemComposers=" + NOmemComposers +
                ", NOmemWritters=" + NOmemWritters +
                ", delSinger=" + delSinger +
                ", delComposers=" + delComposers +
                ", delWritters=" + delWritters +
                ", info=" + info +
                ", songID=" + songID +
                ", reqType=" + reqType +
                ", POSTsingers=" + POSTsingers +
                ", POSTcomposers=" + POSTcomposers +
                ", POSTwriters=" + POSTwriters +
                ", POSTinfo=" + POSTinfo +
                ", allNoneMemberNames=" + allNoneMemberNames +
                '}';
    }

    public ArrayList<String> getPOSTinfo() {
        return POSTinfo;
    }

    public void setPOSTinfo(ArrayList<String> POSTinfo) {
        this.POSTinfo = POSTinfo;
    }

    public ArrayList<ArrayList<String>> getPOSTsingers() {
        return POSTsingers;
    }

    public void setPOSTsingers(ArrayList<ArrayList<String>> POSTsingers) {
        this.POSTsingers = POSTsingers;
    }

    public ArrayList<ArrayList<String>> getPOSTcomposers() {
        return POSTcomposers;
    }

    public void setPOSTcomposers(ArrayList<ArrayList<String>> POSTcomposers) {
        this.POSTcomposers = POSTcomposers;
    }

    public ArrayList<ArrayList<String>> getPOSTwriters() {
        return POSTwriters;
    }

    public void setPOSTwriters(ArrayList<ArrayList<String>> POSTwriters) {
        this.POSTwriters = POSTwriters;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public List<String> getMemSingers() {
        return memSingers;
    }

    public void setMemSingers(List<String> memSingers) {
        this.memSingers = memSingers;
    }

    public List<String> getMemComposers() {
        return memComposers;
    }

    public void setMemComposers(List<String> memComposers) {
        this.memComposers = memComposers;
    }

    public List<String> getMemWritters() {
        return memWritters;
    }

    public void setMemWritters(List<String> memWritters) {
        this.memWritters = memWritters;
    }

    public List<String> getNOmemSingers() {
        return NOmemSingers;
    }

    public void setNOmemSingers(List<String> NOmemSingers) {
        this.NOmemSingers = NOmemSingers;
    }

    public List<String> getNOmemComposers() {
        return NOmemComposers;
    }

    public void setNOmemComposers(List<String> NOmemComposers) {
        this.NOmemComposers = NOmemComposers;
    }

    public List<String> getNOmemWritters() {
        return NOmemWritters;
    }

    public void setNOmemWritters(List<String> NOmemWritters) {
        this.NOmemWritters = NOmemWritters;
    }

    public List<String> getDelSinger() {
        return delSinger;
    }

    public void setDelSinger(List<String> delSinger) {
        this.delSinger = delSinger;
    }

    public List<String> getDelComposers() {
        return delComposers;
    }

    public void setDelComposers(List<String> delComposers) {
        this.delComposers = delComposers;
    }

    public List<String> getDelWritters() {
        return delWritters;
    }

    public void setDelWritters(List<String> delWritters) {
        this.delWritters = delWritters;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public ArrayList<String> getAllNoneMemberNames() {
        return allNoneMemberNames;
    }

    public void setAllNoneMemberNames(ArrayList<String> allNoneMemberNames) {
        this.allNoneMemberNames = allNoneMemberNames;
    }
}
