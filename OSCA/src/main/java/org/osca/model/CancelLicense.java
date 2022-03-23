package org.osca.model;

public class CancelLicense {

    int licenseID;

    public CancelLicense(int licenseID) {
        this.licenseID = licenseID;
    }

    public int getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(int licenseID) {
        this.licenseID = licenseID;
    }

    @Override
    public String toString() {
        return "CancelLicense{" +
                "licenseID=" + licenseID +
                '}';
    }
}
