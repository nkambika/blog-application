package com.myprojects.services;

import com.myprojects.dtos.PermissionRequestDto;
import com.myprojects.dtos.PermissionResponseDto;
import com.myprojects.entities.Permission;
import com.myprojects.exceptions.NoContentsAvailableException;
import com.myprojects.exceptions.ResourceAlreadyExistsException;
import com.myprojects.exceptions.ResourceNotFoundException;
import com.myprojects.repositories.PermissionRepository;
import com.myprojects.utilities.CommonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService implements CommonService<PermissionRequestDto, Long>{
    private final PermissionRepository permissionRepository;

    @Override
    public ResponseEntity<?> create(PermissionRequestDto permissionRequestDto) {
        if(isExistsPermission(permissionRequestDto.getName())){
            throw new ResourceAlreadyExistsException(permissionRequestDto.getName()+" is already exist !!!");
        }
        Permission permission = CommonUtility.convertPermissionRequestDtoToPermission(permissionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionRepository.save(permission));
    }

    @Override
    public ResponseEntity<?> update(PermissionRequestDto permissionRequestDto, Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("permission with id: "+id+" not found!!!"));
        if(isExistsPermission(permissionRequestDto.getName()) && !permission.getName().equals(permissionRequestDto.getName())){
            throw new ResourceAlreadyExistsException(permissionRequestDto.getName()+" is already exist !!!");
        }
        BeanUtils.copyProperties(permissionRequestDto, permission, "id");
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionRepository.save(permission));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("permission with id: "+id+" not found!!!"));
        permission.setStatus(false);
        permissionRepository.save(permission);
        return ResponseEntity.status(HttpStatus.OK).body("permission with id: "+id+" updated successfully!!!");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("permission with id: "+id+" not found!!!"));
        PermissionResponseDto permissionResponseDto = CommonUtility.convertPermissionToPermissionResponseDto(permission);
        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDto);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Permission> permissionList = permissionRepository.findAll();
        if (permissionList.isEmpty()){
            throw new NoContentsAvailableException("No permission data available!!!");
        }
        List<PermissionResponseDto> permissionResponseDtoList = permissionList.stream().map(CommonUtility::convertPermissionToPermissionResponseDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(permissionResponseDtoList);
    }

    public boolean isExistsPermission(String permissionName){
        return permissionRepository.existsByName(permissionName);
    }
}
