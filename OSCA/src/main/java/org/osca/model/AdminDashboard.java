package org.osca.model;

public class AdminDashboard {
    int utype;
    int id;
    String nic;
    String fname;
    String lname;
    String email;
    String phoneNo;
    int SOnum;
    int Mnum;
    int LicenseReqnum;
    int SongReqnum;
    Double memberIncome;
    int concerts;
    Double oscaIncome;
    String DPpath;

    public AdminDashboard() {
    }

    public AdminDashboard(int utype, int id, String fname, String lname, String email, String phoneNo, int SOnum, int mnum, int licenseReqnum, int songReqnum, Double memberIncome, int concerts, Double oscaIncome, String DPpath) {
        this.utype = utype;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.SOnum = SOnum;
        Mnum = mnum;
        LicenseReqnum = licenseReqnum;
        SongReqnum = songReqnum;
        this.memberIncome = memberIncome;
        this.concerts = concerts;
        this.oscaIncome = oscaIncome;
        this.DPpath = DPpath;
    }

    public AdminDashboard(int utype, int id, String fname, String lname, String email, String phoneNo, int SOnum, int mnum, int licenseReqnum, int songReqnum, Double memberIncome, int concerts, Double oscaIncome) {
        this.utype = utype;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.SOnum = SOnum;
        Mnum = mnum;
        LicenseReqnum = licenseReqnum;
        SongReqnum = songReqnum;
        this.memberIncome = memberIncome;
        this.concerts = concerts;
        this.oscaIncome = oscaIncome;
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

    public int getSOnum() {
        return SOnum;
    }

    public void setSOnum(int SOnum) {
        this.SOnum = SOnum;
    }

    public int getMnum() {
        return Mnum;
    }

    public void setMnum(int mnum) {
        Mnum = mnum;
    }

    public int getLicenseReqnum() {
        return LicenseReqnum;
    }

    public void setLicenseReqnum(int licenseReqnum) {
        LicenseReqnum = licenseReqnum;
    }

    public int getSongReqnum() {
        return SongReqnum;
    }

    public void setSongReqnum(int songReqnum) {
        SongReqnum = songReqnum;
    }

    public Double getMemberIncome() {
        return memberIncome;
    }

    public void setMemberIncome(Double memberIncome) {
        this.memberIncome = memberIncome;
    }

    public int getConcerts() {
        return concerts;
    }

    public void setConcerts(int concerts) {
        this.concerts = concerts;
    }

    public Double getOscaIncome() {
        return oscaIncome;
    }

    public void setOscaIncome(Double oscaIncome) {
        this.oscaIncome = oscaIncome;
    }

    @Override
    public String toString() {
        return "AdminDashboard{" +
                "utype=" + utype +
                ", id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", SOnum=" + SOnum +
                ", Mnum=" + Mnum +
                ", LicenseReqnum=" + LicenseReqnum +
                ", SongReqnum=" + SongReqnum +
                ", memberIncome=" + memberIncome +
                ", concerts=" + concerts +
                ", oscaIncome=" + oscaIncome +
                '}';
    }
}
