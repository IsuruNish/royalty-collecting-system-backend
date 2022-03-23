package org.osca.model;

import java.util.ArrayList;

public class MCheckStat {

    ArrayList<ArrayList<Double>> monthDetails;
    ArrayList<ArrayList<Double>> yearDetails;
    int uType;
    private String dpPath;
    private String fName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getuType() {
        return uType;
    }

    public void setuType(int uType) {
        this.uType = uType;
    }




    public String getDpPath() {
        return dpPath;
    }

    public void setDpPath(String dpPath) {
        this.dpPath = dpPath;
    }

    public MCheckStat() {

    }

    public ArrayList<ArrayList<Double>> getMonthDetails() {
        return monthDetails;
    }

    public void setMonthDetails(ArrayList<ArrayList<Double>> monthDetails) {
        this.monthDetails = monthDetails;
    }

    public ArrayList<ArrayList<Double>> getYearDetails() {
        return yearDetails;
    }

    public void setYearDetails(ArrayList<ArrayList<Double>> yearDetails) {
        this.yearDetails = yearDetails;
    }

    @Override
    public String toString() {
        return "SOCheckStat{" +
                "monthDetails=" + monthDetails +
                ", yearDetails=" + yearDetails +
                '}';
    }

}
