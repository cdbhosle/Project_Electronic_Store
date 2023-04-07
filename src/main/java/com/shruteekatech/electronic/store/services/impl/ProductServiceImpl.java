package com.shruteekatech.electronic.store.services.impl;

import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.ProductDto;
import com.shruteekatech.electronic.store.entities.Product;
import com.shruteekatech.electronic.store.exceptions.ResourceNotFoundException;
import com.shruteekatech.electronic.store.helper.Helper;
import com.shruteekatech.electronic.store.repositories.ProductRepository;
import com.shruteekatech.electronic.store.services.ProductServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl implements ProductServiceI {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDto create(ProductDto productDto) {
        log.info("Initiating the dao call for the create product");
        Product product = modelMapper.map(productDto, Product.class);
        Product saveProduct = productRepository.save(product);
        log.info("Completed the dao call for the create product");
        return modelMapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found of given id!!"));
        log.info("Initiating the dao call for the update product");
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        Product updatedProduct = productRepository.save(product);
        log.info("Completed the dao call for the  update product");
        return modelMapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(Long productId) {
        log.info("Initiating the dao call for the  delete product");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found of given id!!"));
        productRepository.delete(product);
        log.info("Completed the dao call for the  delete product");
    }

    @Override
    public ProductDto get(Long productId) {
        log.info("Initiating the dao call for the  get product");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found of given id!!"));
        log.info("Completed the dao call for the  get product");
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {
        log.info("Initiating the dao call for the  get product");
        Sort sort =(sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).ascending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> page = productRepository.findAll(pageable);
        log.info("Completed the dao call for the  get product");
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir) {
        log.info("Initiating the dao call for the  get product");
        Sort sort =(sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).ascending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        log.info("Completed the dao call for the  get product");
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto>  searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir) {
        log.info("Initiating the dao call for the  get product");
        Sort sort =(sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).ascending()):(Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> page = productRepository.findByTitleContaining(subTitle, pageable);
        log.info("Completed the dao call for the  get product");
        return Helper.getPageableResponse(page,ProductDto.class);
    }
}

