package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.ProductCreateRequest;
import com.bkap.techshop.dto.response.ProductResponse;
import com.bkap.techshop.entity.Category;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.CategoryRepository;
import com.bkap.techshop.repository.ProductRepository;
import com.bkap.techshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((element) -> modelMapper.map(element, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse createProduct(ProductCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = modelMapper.map(request, Product.class);
        product.setCategory(category);

        //Xu ly lưu file ảnh
        String imagePath = saveImage(request.getImage());
        product.setImage(imagePath);

        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(long id, ProductCreateRequest request) {

        //find category by id
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        //find product by id
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        modelMapper.map(request, product);

        product.setCategory(category);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            //save image into folder
            String imagePath = saveImage(request.getImage());
            product.setImage(imagePath);
        }

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductResponse.class);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<ProductResponse> findByProductName(String name) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(name);
        return products.stream().map((element) -> modelMapper.map(element, ProductResponse.class))
                .collect(Collectors.toList());
    }

    private String saveImage(MultipartFile imageFile) {
        String fileName = imageFile.getOriginalFilename();

        try{
            Path uploadPath = Paths.get("src/main/resources/uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (IOException e){
            throw new RuntimeException("Fail to store file", e);
        }

    }
}
