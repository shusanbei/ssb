package edu.hue.jk.model;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String telephone;
    private String introduce;
    private String activeCode;
    private Integer state;
    private String role;
    private Date registTime;
}
