package com.myprojects.controllers;

import com.myprojects.dtos.PermissionRequestDto;
import com.myprojects.services.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@Validated
public class PermissionController {
    private final PermissionService permissionService;
    @PostMapping("/create")
    public ResponseEntity<?> createPermission(@Valid @RequestBody PermissionRequestDto permissionRequestDto){
        return permissionService.create(permissionRequestDto);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updatePermission(@Valid @RequestBody PermissionRequestDto permissionRequestDto, Long id){
        return permissionService.update(permissionRequestDto, id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Long id){
        return permissionService.delete(id);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPermission(@PathVariable Long id){
        return permissionService.get(id);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPermissions(){
        return permissionService.getAll();
    }
}
