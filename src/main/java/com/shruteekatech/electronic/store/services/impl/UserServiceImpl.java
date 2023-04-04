package com.shruteekatech.electronic.store.services.impl;

import com.shruteekatech.electronic.store.dtos.CategoryDto;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.UserDto;
import com.shruteekatech.electronic.store.entities.User;
import com.shruteekatech.electronic.store.exceptions.ResourceNotFoundException;
import com.shruteekatech.electronic.store.helper.Helper;
import com.shruteekatech.electronic.store.repositories.UserRepository;
import com.shruteekatech.electronic.store.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserServiceI {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${user.profile.image.path}")
    private String imagePath;


    @Override
    public UserDto createUser(UserDto userDto) {
        //generate unique id in string format
//        String userId = UUID.randomUUID().toString();
//        userDto.setUserId((int) Long.parseLong(userId));
        //dto to entity
        log.info("Initiating the dao call for the save user data");
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        //entity to dto
        UserDto newDto = entityToDto(savedUser);
        log.info("Completed the dao call for the save user data");
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        log.info("Initiating the dao call for the update user data");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with given id "));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        log.info("Completed the dao call for the update user data");
        return updatedDto;
    }


    @Override
    public void deleteUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user with this id is deleted "));

        //delete user profile image
        log.info("Initiating the dao call for the delete user data");
        userRepository.delete(user);
        log.info("Completed the dao call for the delete user data");
        String fullPath = imagePath + user.getImageName();
        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException ex) {
            log.info("User image not found in folder");
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNUmber, int pageSize, String sortBy, String sortDir) {
        log.info("Initiating the dao call for the get all users data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        //page num default starts from 0
        PageRequest pageable = PageRequest.of(pageNUmber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);
        log.info("Completed the dao call for the get all users data");
        return response;
    }


    @Override
    public UserDto getUserById(Integer userId) {
        log.info("Initiating the dao call for the get user by id data");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with given id"));
        log.info("Completed the dao call for the get user by id data");
        return this.entityToDto(user);
    }


    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Initiating the dao call for getUserByEmail");
        User userEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with given Email"));
        log.info("Completed the dao call for getUserByEmail");
        return this.entityToDto(userEmail);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        log.info("Initiating the dao call for searchUser");
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        log.info("Completed the dao call for searchUser");
        return dtoList;
    }

    private UserDto entityToDto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();


        return modelMapper.map(savedUser, UserDto.class);

    }

    private User dtoToEntity(UserDto userDto) {

//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
        return modelMapper.map(userDto, User.class);


    }

    public static interface CategoryServiceI {
        //create
        CategoryDto create(CategoryDto categoryDto);

        //update
        CategoryDto update(CategoryDto categoryDto, int categoryId);

        //delete
        void delete(int categoryId);

        //getall

        PageableResponse<CategoryDto> getAll();
        //get single category detail
        CategoryDto get(int categoryId);



    }
}
