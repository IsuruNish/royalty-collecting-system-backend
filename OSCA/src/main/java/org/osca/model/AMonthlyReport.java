package org.osca.model;

import java.util.ArrayList;

public class AMonthlyReport {

    private ArrayList<ArrayList<String>>  incomingDetails;
    private ArrayList<ArrayList<String>>  outgoingDetails;
    int uType;
    private String dpPath;
    private String fName;

    private ArrayList<ArrayList<String>> tableDataArr;

    public AMonthlyReport() {
    }

    public AMonthlyReport(ArrayList<ArrayList<String>> incomingDetails, ArrayList<ArrayList<String>> outgoingDetails, int uType, String dpPath, String fName) {
        this.incomingDetails = incomingDetails;
        this.outgoingDetails = outgoingDetails;
        this.uType = uType;
        this.dpPath = dpPath;
        this.fName = fName;
    }

    public ArrayList<ArrayList<String>> getIncomingDetails() {
        return incomingDetails;
    }

    public void setIncomingDetails(ArrayList<ArrayList<String>> incomingDetails) {
        this.incomingDetails = incomingDetails;
    }

    public ArrayList<ArrayList<String>> getOutgoingDetails() {
        return outgoingDetails;
    }

    public void setOutgoingDetails(ArrayList<ArrayList<String>> outgoingDetails) {
        this.outgoingDetails = outgoingDetails;
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public ArrayList<ArrayList<String>> getTableDataArr() {
        return tableDataArr;
    }

    public void setTableDataArr(ArrayList<ArrayList<String>> tableDataArr) {
        this.tableDataArr = tableDataArr;
    }

    @Override
    public String toString() {
        return "AMonthlyReport{" +
                "incomingDetails=" + incomingDetails +
                ", outgoingDetails=" + outgoingDetails +
                ", uType=" + uType +
                ", dpPath='" + dpPath + '\'' +
                ", fName='" + fName + '\'' +
                ", tableDataArr=" + tableDataArr +
                '}';
    }
}
