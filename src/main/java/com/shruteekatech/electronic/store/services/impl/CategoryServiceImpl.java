package com.shruteekatech.electronic.store.services.impl;

import com.shruteekatech.electronic.store.dtos.CategoryDto;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.entities.Category;
import com.shruteekatech.electronic.store.exceptions.ResourceNotFoundException;
import com.shruteekatech.electronic.store.helper.Helper;
import com.shruteekatech.electronic.store.repositories.CategoryRepository;
import com.shruteekatech.electronic.store.services.CategoryServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryServiceI {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        //creating categoryId :randomely
        log.info("Initiating the dao call for the create category");
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        log.info("Completed the dao call for the create category");
        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, int categoryId) {

        //get category id
        log.info("Initiating the dao call for the update category data");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updateCategory = categoryRepository.save(category);
        log.info("Completed the dao call for the update category data");
        return modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void delete(int categoryId) {
        log.info("Initiating the dao call for the delete category data");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));
        categoryRepository.delete(category);
        log.info("Completed the dao call for the delete category data");

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        log.info("Initiating the dao call for the get all category data");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Sort sort = (sortBy.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        log.info("Completed the dao call for the get all category data");
        return pageableResponse;
    }

    @Override
    public CategoryDto get(int categoryId) {
        log.info("Initiating the dao call for the get category by id data");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not foundException"));
        log.info("Completed the dao call for the get category by id data");
        return modelMapper.map(category, CategoryDto.class);
    }
}
