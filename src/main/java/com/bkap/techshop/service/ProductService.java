package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.ProductRequest;
import com.bkap.techshop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(long id, ProductRequest request);
    void deleteProduct(long id);
    List<ProductResponse> findByProductName(String name);

}
