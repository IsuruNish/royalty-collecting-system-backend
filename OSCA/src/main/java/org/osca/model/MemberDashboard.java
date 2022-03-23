package org.osca.model;

import java.util.ArrayList;

public class MemberDashboard {
    private int id;
    private String nic;
    private String fname;
    private String lname;
    private String email;
    private String phoneNo;
    private String password;
    private String DPpath;
    private String bankName;
    private String bankBranch;
    private String accNo;
    private String activeStatus;
    private int deleteStatus;
    private int userType;
    private int deletedBy;
    private String deletedOn;
    private String token;
    private String reqType;
    private double upcomingIncome;
    private double pastIncome;

    private ArrayList<String> memberNames;
    private ArrayList<String> memberIDs;

    public MemberDashboard() {
    }

    public MemberDashboard(int userType, ArrayList<String> memberNames, ArrayList<String> memberIDs, String DPpath, String fname) {
        this.userType = userType;
        this.memberNames = memberNames;
        this.memberIDs = memberIDs;
        this.DPpath = DPpath;
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDPpath() {
        return DPpath;
    }

    public void setDPpath(String DPpath) {
        this.DPpath = DPpath;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public double getUpcomingIncome() {
        return upcomingIncome;
    }

    public void setUpcomingIncome(double upcomingIncome) {
        this.upcomingIncome = upcomingIncome;
    }

    public double getPastIncome() {
        return pastIncome;
    }

    public void setPastIncome(double pastIncome) {
        this.pastIncome = pastIncome;
    }

    public ArrayList<String> getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(ArrayList<String> memberNames) {
        this.memberNames = memberNames;
    }

    public ArrayList<String> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(ArrayList<String> memberIDs) {
        this.memberIDs = memberIDs;
    }


    @Override
    public String toString() {
        return "MemberDashboard{" +
                ", id=" + id +
                ", nic='" + nic + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                ", DPpath='" + DPpath + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankBranch='" + bankBranch + '\'' +
                ", accNo='" + accNo + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", userType=" + userType +
                ", deletedBy=" + deletedBy +
                ", deletedOn='" + deletedOn + '\'' +
                ", token='" + token + '\'' +
                ", reqType='" + reqType + '\'' +
                ", upcomingIncome=" + upcomingIncome +
                ", pastIncome=" + pastIncome +
                ", memberNames=" + memberNames +
                ", memberIDs=" + memberIDs +
                '}';
    }
}
