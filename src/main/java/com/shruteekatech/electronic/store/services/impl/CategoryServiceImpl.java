package com.shruteekatech.electronic.store.services.impl;

import com.shruteekatech.electronic.store.dtos.CategoryDto;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.entities.Category;
import com.shruteekatech.electronic.store.exceptions.ResourceNotFoundException;
import com.shruteekatech.electronic.store.helper.Helper;
import com.shruteekatech.electronic.store.repositories.CategoryRepository;
import com.shruteekatech.electronic.store.services.CategoryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryServiceI {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);

        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, int categoryId) {

        //get category id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updateCategory = categoryRepository.save(category);
        return modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void delete(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));
        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Sort sort=(sortBy.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);

        return pageableResponse;
    }

    @Override
    public CategoryDto get(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));
        return modelMapper.map(category, CategoryDto.class);
    }
}
