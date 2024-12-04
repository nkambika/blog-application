package com.myprojects.controllers;

import com.myprojects.dtos.UserRequestDto;
import com.myprojects.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequestDto userRequestDto,@PathVariable Long id){
        return userService.update(userRequestDto, id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.delete(id);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userService.get(id);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAll();
    }
    @GetMapping("/get-users-by-wild-search")
    public ResponseEntity<?> getUserByWildSearch(
            @RequestParam String search,
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
       return userService.getUsersByWildSearch(search,pageNumber,pageSize,sortBy,sortDir);
    }
}
