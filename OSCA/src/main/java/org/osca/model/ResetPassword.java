package org.osca.model;

public class ResetPassword {

    private String pin;
    private  String pass1;

    public ResetPassword(String pin, String pass1) {
        this.pin = pin;
        this.pass1 = pass1;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    @Override
    public String toString() {
        return "ResetPassword{" +
                "pin='" + pin + '\'' +
                ", pass1='" + pass1 + '\'' +
                '}';
    }
}
