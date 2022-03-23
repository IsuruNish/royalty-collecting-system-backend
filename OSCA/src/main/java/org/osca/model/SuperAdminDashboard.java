package org.osca.model;

public class SuperAdminDashboard {
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

    int emailFlag;

    public SuperAdminDashboard(int utype, String fname, String DPpath) {
        this.utype = utype;
        this.fname = fname;
        this.DPpath = DPpath;
    }

    public SuperAdminDashboard(int utype, int id, String fname, String lname, String email, String phoneNo, int SOnum, int mnum, int licenseReqnum, int songReqnum, Double memberIncome, int concerts, Double oscaIncome, String DPpath) {
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

    public SuperAdminDashboard(int utype, String fname, String lname, String nic, String email, String phoneNo, String DPpath) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
        this.DPpath = DPpath;
    }

    public SuperAdminDashboard(int utype, String nic, String fname, String lname, String email, String phoneNo) {
        this.utype = utype;
        this.nic = nic;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public SuperAdminDashboard(int utype) {
        this.utype = utype;
    }

    @Override
    public String toString() {
        return "SuperAdminDashboard{" +
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

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDPpath() {
        return DPpath;
    }

    public void setDPpath(String DPpath) {
        this.DPpath = DPpath;
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

    public int getEmailFlag() {
        return emailFlag;
    }

    public void setEmailFlag(int emailFlag) {
        this.emailFlag = emailFlag;
    }

    public SuperAdminDashboard() {
    }
}
