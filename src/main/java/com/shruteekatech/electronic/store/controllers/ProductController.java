package com.shruteekatech.electronic.store.controllers;

import com.shruteekatech.electronic.store.dtos.ApiResponse;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.ProductDto;
import com.shruteekatech.electronic.store.services.ProductServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceI productServiceI;
    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
    {
        log.info("Entering the request for createProduct ");
        ProductDto createProduct = productServiceI.create(productDto);
        log.info("completed the request for createProduct");
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long productId, @RequestBody ProductDto productDto)
    {
        log.info("Entering the request for updateProduct ");
        ProductDto updatedProduct = productServiceI.update(productDto,productId);
        log.info("completed the request for updateProduct ");
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable long productId)
    {
        log.info("Entering the request for deleteProduct ");
        productServiceI.delete(productId);
        ApiResponse responseMessage = ApiResponse.builder().message("Product is deleted successfully").success(true).build();
        log.info("completed the request for deleteProduct ");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long productId)
    {   log.info("Entering the request for getProduct ");
        ProductDto productDto = productServiceI.get(productId);
        log.info("Completed the request for getProduct ");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct( @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                       @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                       @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {   log.info("Entering the request for getAllProduct ");
        PageableResponse<ProductDto> pageableResponse = productServiceI.getAll(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed the request for getAllProduct ");
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive( @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                       @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                       @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        log.info("Entering the request for getAllLive ");
        PageableResponse<ProductDto> pageableResponse = productServiceI.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed the request for getAllLive ");
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                    @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                    @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {   log.info("Entering the request for searchProduct ");
        PageableResponse<ProductDto> pageableResponse = productServiceI.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed the request for searchProduct ");
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


}
