package com.helidon.ums.Dto;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"userid","firstName","lastName","email","password","user_role"})
public class UserDetails {

    private Integer userid;

    private String firstName;

    private   String lastName;

    private String email;

    private   String password;

    private String user_role;

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_role() {
        return user_role;
    }

    public UserDetails() {
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userid=" + userid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user_role='" + user_role + '\'' +
                '}';
    }
}
