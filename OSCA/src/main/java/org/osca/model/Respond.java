package org.osca.model;

public class Respond {
    int ok;
    int userType;
    int numbers;

    public Respond(int ok) {
        this.ok = ok;
    }

    public Respond(int ok, int numbers) {
        this.ok = ok;
        this.numbers = numbers;
    }

    public Respond() {
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Respond{" +
                "ok=" + ok +
                ", userType=" + userType +
                ", numbers=" + numbers +
                '}';
    }
}
