package com.shruteekatech.electronic.store.services;

import com.shruteekatech.electronic.store.dtos.CategoryDto;
import com.shruteekatech.electronic.store.dtos.PageableResponse;

public interface CategoryServiceI  {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto, int categoryId);

    void delete(int categoryId);

    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

    CategoryDto get(int categoryId);



}
