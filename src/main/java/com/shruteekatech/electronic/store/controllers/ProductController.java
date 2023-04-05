package com.shruteekatech.electronic.store.controllers;

import com.shruteekatech.electronic.store.dtos.ApiResponse;
import com.shruteekatech.electronic.store.dtos.PageableResponse;
import com.shruteekatech.electronic.store.dtos.ProductDto;
import com.shruteekatech.electronic.store.services.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceI productServiceI;
    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
    {
        ProductDto createProduct = productServiceI.create(productDto);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long productId, @RequestBody ProductDto productDto)
    {
        ProductDto updatedProduct = productServiceI.update(productDto,productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable long productId)
    {
        productServiceI.delete(productId);
        ApiResponse responseMessage = ApiResponse.builder().message("Product is deleted successfully").success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long productId)
    {
        ProductDto productDto = productServiceI.get(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<PageableResponse<ProductDto>> getAllProduct( @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                       @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                       @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<ProductDto> pageableResponse = productServiceI.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive( @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                       @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                       @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<ProductDto> pageableResponse = productServiceI.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                                    @RequestParam(value = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                    @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<ProductDto> pageableResponse = productServiceI.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


}
