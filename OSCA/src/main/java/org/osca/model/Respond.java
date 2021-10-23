package org.osca.model;

public class Respond {
    int ok;
    int userType;

    public Respond(int ok) {
        this.ok = ok;
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
}
