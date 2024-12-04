package com.myprojects.services;

import com.myprojects.dtos.UserRequestDto;
import com.myprojects.dtos.UserResponseDto;
import com.myprojects.entities.User;
import com.myprojects.exceptions.NoContentsAvailableException;
import com.myprojects.exceptions.ResourceAlreadyExistsException;
import com.myprojects.exceptions.ResourceNotFoundException;
import com.myprojects.repositories.UserRepository;
import com.myprojects.utilities.CommonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements CommonService<UserRequestDto, Long>{
    private final UserRepository userRepository;
    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public ResponseEntity<?> create(UserRequestDto userRequestDto) {
        if(isEmailExists(userRequestDto.getEmail())){
            throw new ResourceAlreadyExistsException("email ["+userRequestDto.getEmail()+"] already exists !!!");
        }
        if(isMobileExists(userRequestDto.getMobile())){
            throw new ResourceAlreadyExistsException("mobile number ["+userRequestDto.getMobile()+"] already exists !!!");
        }
        User user = CommonUtility.convertUserRequestDtoToUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public ResponseEntity<?> update(UserRequestDto userRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user with id: "+id+" not found!!!"));
        if(isEmailExists(userRequestDto.getEmail()) && !userRequestDto.getEmail().equals(user.getEmail())){
            throw new ResourceAlreadyExistsException(userRequestDto.getEmail()+" already exists !!!");
        }
        if(isMobileExists(userRequestDto.getMobile()) && !userRequestDto.getMobile().equals(user.getMobile())){
            throw new ResourceAlreadyExistsException(userRequestDto.getMobile()+" already exists !!!");
        }
        BeanUtils.copyProperties(userRequestDto, user);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("user with id: "+id+" updated successfully!!!");
    }

    @Override
    @Transactional(rollbackFor = DataAccessException.class)
    public ResponseEntity<?> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user with id: "+id+" not found!!!"));
        user.setStatus(false);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("user with id: "+id+" deleted successfully!!!");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user with id: "+id+" not found!!!"));
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new NoContentsAvailableException("No users available!!!");
        }
        List<UserResponseDto> userResponseDtoList = users.stream().map(CommonUtility::convertUserToUserResponseDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }
    public ResponseEntity<?> getUsersByWildSearch(String search, Integer pageNumber, Integer pageSize, String sortBy, String sortDir ){
        Sort.Direction sortDirection = sortDir.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortDirection,sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> userPage = userRepository.findByWildSearch(search, pageRequest);
        List<UserResponseDto> userResponseDtoList = userPage.stream().map(CommonUtility::convertUserToUserResponseDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }
    public boolean isMobileExists(String mobile){
        return userRepository.existsByMobile(mobile);
    }
    public boolean isEmailExists(String email){
        return userRepository.existsByEmail(email);
    }
}
