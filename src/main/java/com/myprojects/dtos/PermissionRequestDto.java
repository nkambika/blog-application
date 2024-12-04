package com.myprojects.dtos;

import com.myprojects.utilities.CommonConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PermissionRequestDto {
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = CommonConstants.NAME_REGEXP, message = CommonConstants.INVALID_NAME_MESSAGE)
    private String name;
    private List<String> allowed_endpoints = new ArrayList<>();
    private List<String> allowed_methods = new ArrayList<>();
}
