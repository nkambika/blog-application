package com.myprojects.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserResponseDto extends CommonResponseDto{
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private LocalDate dob;
    private String about;
}
