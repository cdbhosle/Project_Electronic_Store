package com.shruteekatech.electronic.store.controllers;

import com.shruteekatech.electronic.store.dtos.ApiResponse;
import com.shruteekatech.electronic.store.dtos.ImageResponse;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.UserDto;
import com.shruteekatech.electronic.store.services.FileService;
import com.shruteekatech.electronic.store.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServiceI userServiceI;

    @Autowired
    private FileService fileService;


    @Value("${user.profile.image.path}")
    private String imageUploadPath;


    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Entering the request for create user ");
        UserDto createUserDto = this.userServiceI.createUser(userDto);
        log.info("completed the request for create user");
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable Integer userId) {
        log.info("Entering the request for update the user with userId{}: ", userId);
        UserDto updatedUser = this.userServiceI.updateUser(userdto, userId);
        log.info("completed the request for update the user with userId{}: ", userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) throws IOException {
        log.info("Entering the request for deleteUser with userId{}:", uid);
        this.userServiceI.deleteUser(uid);
        log.info("Completed the request for deleteUser with userId{}:", uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                 @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
                                                                 @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        log.info("Entering the request for getAllUsers ");
        this.userServiceI.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed the request for getAllUsers");
        return new ResponseEntity<>(this.userServiceI.getAllUser(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }


    //@PathVariable Integer userId

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {

        log.info("Entering the request for getSingleUser with userId{}:",userId);
        this.userServiceI.getUserById(userId);

        log.info("completed the request for getSingleUser with userId{}:",userId);
        return new ResponseEntity<>(this.userServiceI.getUserById(userId), HttpStatus.OK);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        log.info("Entering the request for getUserByEmail with email{}",email);
        this.userServiceI.getUserByEmail(email);
        log.info("Completed the request for getUserByEmail with email{}",email);
        return new ResponseEntity<>(this.userServiceI.getUserByEmail(email), HttpStatus.OK);

    }

    @GetMapping("/search{keywords}")
    public ResponseEntity<List<UserDto>> searchUsers(@PathVariable String keywords) {
        log.info("Entering the request for searchUsers");
        this.userServiceI.searchUser(keywords);
        log.info("Completed the request for searchUsers");
        return new ResponseEntity<>(this.userServiceI.searchUser(keywords), HttpStatus.OK);

    }


    //upload image

    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("UserImage") MultipartFile image, @PathVariable int userId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        log.info("Entering the request for uploadUserImage");
        UserDto user = userServiceI.getUserById(userId);
        user.setImageName(imageName);

        UserDto userDto = userServiceI.updateUser(user, userId);


        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        log.info("Completed the request for uploadUserImage");

        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }


}
