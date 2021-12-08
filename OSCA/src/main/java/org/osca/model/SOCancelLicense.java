package org.osca.model;



import java.util.List;

public class SOCancelLicense {

    private List<String> concertID;


    private List<String> concertName;
    private List<String> concertDate;
    private List<String> venue;
    private List<String> concertType;
    private List<String> refundAmount;
    private int utype;
    private String dpPath;
    private String Fname;

    public SOCancelLicense(List<String> concertID, List<String> concertName, List<String> concertDate, List<String> venue, List<String> concertType, List<String> refundAmount) {
        this.concertID = concertID;
        this.concertName = concertName;
        this.concertDate = concertDate;
        this.venue = venue;
        this.concertType = concertType;
        this.refundAmount = refundAmount;
        this.utype = utype;
        this.dpPath = dpPath;
        this.Fname = Fname;
    }

    public String getDpPath() {
        return dpPath;
    }

    public void setDpPath(String dpPath) {
        this.dpPath = dpPath;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public List<String> getConcertID() {
        return concertID;
    }

    public void setConcertID(List<String> concertID) {
        this.concertID = concertID;
    }

    public List<String> getConcertName() {
        return concertName;
    }

    public void setConcertName(List<String> concertName) {
        this.concertName = concertName;
    }

    public List<String> getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(List<String> concertDate) {
        this.concertDate = concertDate;
    }

    public List<String> getVenue() {
        return venue;
    }

    public void setVenue(List<String> venue) {
        this.venue = venue;
    }

    public List<String> getConcertType() {
        return concertType;
    }

    public void setConcertType(List<String> concertType) {
        this.concertType = concertType;
    }

    public List<String> getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(List<String> refundAmount) {
        this.refundAmount = refundAmount;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    @Override
    public String toString() {
        return "SOCancelLicense{" +
                "concertID=" + concertID +
                ", concertName=" + concertName +
                ", concertDate=" + concertDate +
                ", venue=" + venue +
                ", concertType=" + concertType +
                ", refundAmount=" + refundAmount +
                '}';
    }
}
