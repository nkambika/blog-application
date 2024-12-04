package com.myprojects.dtos;

import com.myprojects.utilities.CommonConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = CommonConstants.EMAIL_REGEXP, message = CommonConstants.INVALID_EMAIL_MESSAGE)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "password must be 8-20 characters long")
    @Pattern(regexp = CommonConstants.PASSWORD_REGEXP, message = CommonConstants.INVALID_PASSWORD_MESSAGE)
    private String password;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = CommonConstants.MOBILE_REGEXP, message = CommonConstants.INVALID_MOBILE_MESSAGE)
    private String mobile;

    @Pattern(regexp = CommonConstants.GENDER_REGEXP, message = CommonConstants.INVALID_GENDER_MESSAGE)
    private String gender;

    @PastOrPresent
    private LocalDate dob;

    @Size(max = 500)
    private String about;
}
