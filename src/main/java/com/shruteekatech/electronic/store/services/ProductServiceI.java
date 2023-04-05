package com.shruteekatech.electronic.store.services;

import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductServiceI {

    ProductDto create(ProductDto productDto);

    ProductDto update (ProductDto productDto,Long productId);

    void delete(Long productId);

    ProductDto get(Long productId);

    PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

    PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);



    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);

}
