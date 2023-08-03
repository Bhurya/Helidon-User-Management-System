package com.helidon.ums.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

    private String firstname;

    private   String lastname;

    private String email;

    private   String password;

    private Long roleId;

    @NotNull(message = "User first name must")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @NotNull(message = "User last name must")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Email(message ="Email must be a valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Password must be a valid password")
    @Size(min = 5,max =6 ,message = "Password must be at least max 6 characters and min 5")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Role must be")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
