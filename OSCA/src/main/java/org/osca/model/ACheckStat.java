package org.osca.model;

import java.util.ArrayList;

public class ACheckStat {

    ArrayList<ArrayList<Double>> monthDetails;
    ArrayList<ArrayList<Double>> yearDetails;
    ArrayList<ArrayList<Double>> memberIncomeMonthDetails;
    ArrayList<ArrayList<Double>> memberIncomeYearDetails;

    public ArrayList<ArrayList<Double>> getMemberIncomeMonthDetails() {
        return memberIncomeMonthDetails;
    }

    public void setMemberIncomeMonthDetails(ArrayList<ArrayList<Double>> memberIncomeMonthDetails) {
        this.memberIncomeMonthDetails = memberIncomeMonthDetails;
    }

    public ArrayList<ArrayList<Double>> getMemberIncomeYearDetails() {
        return memberIncomeYearDetails;
    }

    public void setMemberIncomeYearDetails(ArrayList<ArrayList<Double>> memberIncomeYearDetails) {
        this.memberIncomeYearDetails = memberIncomeYearDetails;
    }

    ArrayList<ArrayList<Double>> LicenseIncomeYearDetails;
    ArrayList<ArrayList<Double>> LicenseIncomeMonthDetails;
    ArrayList<ArrayList<Double>> DistributionReportMonthDetails;
    ArrayList<ArrayList<Double>> DistributionReportYearDetails;



    public ArrayList<ArrayList<Double>> getLicenseIncomeYearDetails() {
        return LicenseIncomeYearDetails;
    }

    public void setLicenseIncomeYearDetails(ArrayList<ArrayList<Double>> licenseIncomeYearDetails) {
        LicenseIncomeYearDetails = licenseIncomeYearDetails;
    }

    public ArrayList<ArrayList<Double>> getLicenseIncomeMonthDetails() {
        return LicenseIncomeMonthDetails;
    }

    public void setLicenseIncomeMonthDetails(ArrayList<ArrayList<Double>> licenseIncomeMonthDetails) {
        LicenseIncomeMonthDetails = licenseIncomeMonthDetails;
    }

    public ArrayList<ArrayList<Double>> getDistributionReportMonthDetails() {
        return DistributionReportMonthDetails;
    }

    public void setDistributionReportMonthDetails(ArrayList<ArrayList<Double>> distributionReportMonthDetails) {
        DistributionReportMonthDetails = distributionReportMonthDetails;
    }

    public ArrayList<ArrayList<Double>> getDistributionReportYearDetails() {
        return DistributionReportYearDetails;
    }

    public void setDistributionReportYearDetails(ArrayList<ArrayList<Double>> distributionReportYearDetails) {
        DistributionReportYearDetails = distributionReportYearDetails;
    }

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

    public ACheckStat() {

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
