package org.osca.model;

public class AMonthlyReportsYearAndMonth {
    private int type;
    private String year;
    private String month;
    

    public AMonthlyReportsYearAndMonth() {
    }

    public AMonthlyReportsYearAndMonth(int type, String year, String month) {
        this.type = type;
        year = year;
        month = month;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        month = month;
    }

    @Override
    public String toString() {
        return "AMonthlyReportsYearAndMonth{" +
                "type=" + type +
                ", Year='" + year + '\'' +
                ", Month='" + month + '\'' +
                '}';
    }
}
