package com.snookerbooking.service;

import com.snookerbooking.dto.UserDTO;
import com.snookerbooking.entity.User;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO dto);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    void deleteUser(Long id);

    User updateUser(Long id, User updatedUser);

}
