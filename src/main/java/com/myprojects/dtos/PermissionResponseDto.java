package com.myprojects.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PermissionResponseDto extends CommonResponseDto{
    private String name;
    private List<String> allowed_endpoints;
    private List<String> allowed_methods;
}
