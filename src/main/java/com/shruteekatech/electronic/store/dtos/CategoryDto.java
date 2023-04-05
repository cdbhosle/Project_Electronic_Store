package com.shruteekatech.electronic.store.dtos;

import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryDto {

    private int categoryId;
    @NotBlank(message = "title is require...!!")
    @Min(value = 4, message = "title must be min of 4 characters!!")
    private String title;

    @NotBlank(message = "Description requires..!!")
    private String description;

    private String coverImage;
}
