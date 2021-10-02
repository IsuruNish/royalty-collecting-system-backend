package org.osca.model;

public class ShowOrganizer {
    private int uid;
    private String fname;
    private String lname;
    private String nic;
    private String phone;
    private String email;
    private String password;
    private int deleteStatus;
    private int userType;
    private int deletedBy;
    private String deletedOn;

    private String token;


    public ShowOrganizer() {
    }

    public ShowOrganizer(String fname, String lname, String nic, String phone, String email, String password, String token) {
        this.fname = fname;
        this.lname = lname;
        this.nic = nic;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userType = 5;
        this.token = token;
    }

    public ShowOrganizer(String fname, String lname, String nic, String phone, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.nic = nic;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public ShowOrganizer(int userType, String token) {
        this.userType = userType;
        this.token = token;
    }

    public ShowOrganizer(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public ShowOrganizer(String fname, int userType) {
        this.fname = fname;
        this.userType = userType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "ShowOrganizer{" +
                "uid=" + uid +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", nic='" + nic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", userType=" + userType +
                ", deletedBy=" + deletedBy +
                ", deletedOn='" + deletedOn + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
