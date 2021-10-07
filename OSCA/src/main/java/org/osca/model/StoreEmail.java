package org.osca.model;

public class StoreEmail {

    private static String email;


    public StoreEmail(String email) {
        this.email = email;
    }

    public StoreEmail(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StoreEmail{" +
                "email='" + email + '\'' +
                '}';
    }
}
