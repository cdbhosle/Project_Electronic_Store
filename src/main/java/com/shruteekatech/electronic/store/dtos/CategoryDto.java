package com.shruteekatech.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    private int categoryId;
    @NotBlank
    @Min(value = 4, message = "title must be min of 4 characters")
    private String title;

    @NotBlank(message = "Description requires..!!")
    private String description;

    private String coverImage;
}
