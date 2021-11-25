package org.osca.model;

public class ChangeInfo {
    int utype;
    int id;
    String nic;
    String fname;
    String lname;
    String email;
    String phoneNo;
    String DPpath;


    String bankName;
    String bankBranch;
    String accNo;

    public ChangeInfo() {
    }

    public ChangeInfo(int utype, String fname, String lname, String nic, String email, String phoneNo, String DPpath) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.DPpath = DPpath;
    }

    public ChangeInfo(int utype, String fname, String lname, String nic, String email, String phoneNo) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public ChangeInfo(int utype, String nic, String fname, String lname, String email, String phoneNo, String DPpath, String bankName, String bankBranch, String accNo) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.DPpath = DPpath;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.accNo = accNo;
    }

    public ChangeInfo(int utype, String nic, String fname, String lname, String email, String phoneNo, String bankName, String bankBranch, String accNo) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.accNo = accNo;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
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

    @Override
    public String toString() {
        return "ChangeInfo{" +
                "utype=" + utype +
                ", id=" + id +
                ", nic='" + nic + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", DPpath='" + DPpath + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankBranch='" + bankBranch + '\'' +
                ", accNo='" + accNo + '\'' +
                '}';
    }
}
