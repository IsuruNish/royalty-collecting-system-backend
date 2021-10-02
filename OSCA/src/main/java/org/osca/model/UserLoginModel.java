package org.osca.model;

public class UserLoginModel {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int userType;
    private String token;

    public UserLoginModel() {
    }

    public UserLoginModel(int userType) {
        this.userType = userType;
    }

    public UserLoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserLoginModel(int userType, String token) {
        this.userType = userType;
        this.token = token;
    }

    public UserLoginModel(String email, String firstName, String lastName, int userType) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserLoginModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
