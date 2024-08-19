package com.panfleto.panfleto.services.product;

import com.panfleto.panfleto.DTOs.ProductDto;
import com.panfleto.panfleto.entities.Product;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(long id);

    void deleteProductById(long id);

    Product updateProduct(Long id, JSONObject object, MultipartFile file);

    Product createProduct(Product product);

}