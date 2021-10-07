package org.osca.model;

public class ForgetPassword {

    private String email;
   
    public ForgetPassword(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgetPassword{" +
                "email='" + email + '\'' +
                '}';
    }
}
