package org.osca.model;

public class ChangeInfoUsers {
    private String pass;
    private String newPass;

    public ChangeInfoUsers() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String toString() {
        return "ChangeInfoUsers{" +
                "pass='" + pass + '\'' +
                ", newPass='" + newPass + '\'' +
                '}';
    }
}
