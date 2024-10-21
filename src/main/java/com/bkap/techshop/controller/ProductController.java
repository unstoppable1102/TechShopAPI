package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.ProductCreateRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.ProductResponse;
import com.bkap.techshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@ModelAttribute ProductCreateRequest request){
        ProductResponse productResponse = productService.createProduct(request);

        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(productResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> findAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
        return ApiResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(products)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable long id){
        ProductResponse product = productService.getProductById(id);
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(product)
                .build();
    }

    @GetMapping("/search/{name}")
    public ApiResponse<List<ProductResponse>> findProductByName(@PathVariable String name){
        List<ProductResponse> productResponses = productService.findByProductName(name);
        return ApiResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(productResponses)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable long id, @ModelAttribute ProductCreateRequest request){
        ProductResponse updateProduct = productService.updateProduct(id, request);
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(updateProduct)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct( @PathVariable long id){
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .build();

    }
}
