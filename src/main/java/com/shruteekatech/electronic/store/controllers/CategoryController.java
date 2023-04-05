package com.shruteekatech.electronic.store.controllers;

import com.shruteekatech.electronic.store.dtos.ApiResponse;
import com.shruteekatech.electronic.store.dtos.CategoryDto;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.services.CategoryServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryServiceI categoryServiceI;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {//call service to save object
        CategoryDto categoryDto1 = categoryServiceI.create(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable int categoryId, @RequestBody CategoryDto categoryDto)
    {
        CategoryDto updatedCategory = categoryServiceI.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId)
    {
        categoryServiceI.delete(categoryId);
        ApiResponse responseMessage = ApiResponse.builder().message("category is deleted successfully").success(true).build();


        return new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        this.categoryServiceI.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(this.categoryServiceI.getAll(pageNumber, pageSize, sortBy, sortDir),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable int categoryId)
    {
        CategoryDto categoryDto = categoryServiceI.get(categoryId);

        return  ResponseEntity.ok(categoryDto);
    }
}
