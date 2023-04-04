package com.shruteekatech.electronic.store.services;

import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.UserDto;

import java.io.IOException;
import java.util.List;

public interface UserServiceI {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,Integer userId);

    void deleteUser(Integer userId) throws IOException;

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNUmber, int pageSize, String sortBy, String sortDir);

    //get single user-by id;
    UserDto getUserById(Integer userId);

    //get user-by email
    UserDto getUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);




}
