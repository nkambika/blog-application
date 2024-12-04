package com.myprojects.utilities;

import com.myprojects.dtos.PermissionRequestDto;
import com.myprojects.dtos.PermissionResponseDto;
import com.myprojects.dtos.UserRequestDto;
import com.myprojects.dtos.UserResponseDto;
import com.myprojects.entities.Permission;
import com.myprojects.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtility {
    public static User convertUserRequestDtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        BeanUtils.copyProperties(userRequestDto,user,"id");
        return user;
    }
    public static UserResponseDto convertUserToUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }

    public static Permission convertPermissionRequestDtoToPermission(PermissionRequestDto permissionRequestDto){
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionRequestDto, permission, "id");
        return permission;
    }
    public static PermissionResponseDto convertPermissionToPermissionResponseDto(Permission permission){
        PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
        BeanUtils.copyProperties(permission, permissionResponseDto);
        return permissionResponseDto;
    }
}
