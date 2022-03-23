package org.osca.model;

public class ForgetPassword {
    private int otp;
    private String pass;
    private String email;

    public ForgetPassword() {
    }

    public ForgetPassword(String email) {
        this.email = email;
    }

    public ForgetPassword(int otp, String email) {
        this.otp = otp;
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "ForgetPassword{" +
                "otp='" + otp + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
