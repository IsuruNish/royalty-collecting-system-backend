package org.osca.model;

import java.util.Date;

public class SystemDetails {
    int systemDetailType;
    double commision;
    String commisionDate;
    int cancellationDuration;
    String cancellationDurationDate;
    double cancellationFee;
    String cancellationFeeDate;

    String fname;
    int utype;
    String DPpath;

    public SystemDetails() {
    }

//    public SystemDetails(int systemDetailType, double number, Date relavantDate){
//        this.systemDetailType = systemDetailType;
//
//        if(systemDetailType == 1){
//            commision = number;
//            commisionDate = relavantDate;
//        }
//
//        if(systemDetailType == 2){
//            cancellationDuration = (int) number;
//            cancellationDurationDate = relavantDate;
//        }
//
//        if(systemDetailType == 3){
//            cancellationFee = number;
//            cancellationFeeDate = relavantDate;
//        }
//    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
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

    public double getCommision() {
        return commision;
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }

    public int getSystemDetailType() {
        return systemDetailType;
    }

    public void setSystemDetailType(int systemDetailType) {
        this.systemDetailType = systemDetailType;
    }

    public String getCommisionDate() {
        return commisionDate;
    }

    public void setCommisionDate(String commisionDate) {
        this.commisionDate = commisionDate;
    }

    public int getCancellationDuration() {
        return cancellationDuration;
    }

    public void setCancellationDuration(int cancellationDuration) {
        this.cancellationDuration = cancellationDuration;
    }

    public String getCancellationDurationDate() {
        return cancellationDurationDate;
    }

    public void setCancellationDurationDate(String cancellationDurationDate) {
        this.cancellationDurationDate = cancellationDurationDate;
    }

    public void setCancellationFeeDate(String cancellationFeeDate) {
        this.cancellationFeeDate = cancellationFeeDate;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public String getCancellationFeeDate() {
        return cancellationFeeDate;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }


    @Override
    public String toString() {
        return "SystemDetails{" +
                "systemDetailType=" + systemDetailType +
                ", commision=" + commision +
                ", commisionDate=" + commisionDate +
                ", cancellationDuration=" + cancellationDuration +
                ", cancellationDurationDate=" + cancellationDurationDate +
                ", cancellationFee=" + cancellationFee +
                ", cancellationFeeDate=" + cancellationFeeDate +
                '}';
    }
}
