package com.myprojects.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends CommonEntity{
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String address;
}
