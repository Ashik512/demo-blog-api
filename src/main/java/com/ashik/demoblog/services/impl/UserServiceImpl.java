package com.ashik.demoblog.services.impl;

import com.ashik.demoblog.entities.User;
import com.ashik.demoblog.exceptions.ResourceNotFoundException;
import com.ashik.demoblog.payloads.UserDto;
import com.ashik.demoblog.repositories.UserRepo;
import com.ashik.demoblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = convertToEntity(userDto);

        User savedUser = userRepo.save(user);

        return convertToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);

        return convertToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        return convertToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepo.findAll();

        List<UserDto> userList = users.stream().map((user) -> convertToDto(user)).collect(Collectors.toList());

        return userList;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        userRepo.delete(user);
    }

    private User convertToEntity(UserDto userDto) {

        User user = new User();

        // user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto convertToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());

        return userDto;
    }

}
