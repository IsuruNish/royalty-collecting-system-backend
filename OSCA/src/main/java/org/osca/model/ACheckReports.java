package org.osca.model;

public class ACheckReports {
    private int isChecked;

    public ACheckReports(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "ACheckReports{" +
                "isChecked=" + isChecked +
                '}';
    }
}
