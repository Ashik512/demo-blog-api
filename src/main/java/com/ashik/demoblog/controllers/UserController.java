package com.ashik.demoblog.controllers;

import com.ashik.demoblog.payloads.ApiResponse;
import com.ashik.demoblog.payloads.UserDto;
import com.ashik.demoblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @PostMapping("/")
     public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);

    }

    // update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId ) {

        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);

    }

    // delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {

        userService.deleteUser(userId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    // getting all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {

        List<UserDto> allUser = userService.getAllUser();

        return ResponseEntity.ok(allUser);

    }

    // getting single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {

        UserDto user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

}
