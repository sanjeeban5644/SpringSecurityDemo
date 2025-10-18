package com.sanjeeban.SpringSecurity.DTO;


public class UserDto {
    private String username;
    private String remarks;

    public UserDto() {
    }

    public UserDto(String username,String remarks) {
        this.username = username;
        this.remarks=remarks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
