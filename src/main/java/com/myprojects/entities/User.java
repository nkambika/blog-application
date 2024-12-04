package com.myprojects.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User extends CommonEntity{
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private LocalDate dob;
    private String about;
    @ManyToOne
    private Role role;
}
