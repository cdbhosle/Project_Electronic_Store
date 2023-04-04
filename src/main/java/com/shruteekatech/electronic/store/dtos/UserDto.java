package com.shruteekatech.electronic.store.dtos;

import com.shruteekatech.electronic.store.validate.ImageNameValid;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {


    private int userId;

    @Size(min = 2, max = 15, message = "Invalid Name..!!")
    private String name;

    @Pattern(regexp =  "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid User Email..!")
    @NotNull(message = "Email is required..!!")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Size(min = 4,max = 6,message = "invalid gender..!")
    private String gender;

    @ImageNameValid
    private String imageName;
    @NotBlank(message = "Write something here..!")
    private String about;


}
