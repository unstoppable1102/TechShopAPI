package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.ProductCreateRequest;
import com.bkap.techshop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse getProductById(long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(long id, ProductCreateRequest request);
    void deleteProduct(long id);
    List<ProductResponse> findByProductName(String name);

}
