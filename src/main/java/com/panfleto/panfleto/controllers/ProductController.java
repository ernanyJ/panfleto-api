package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.ProductDto;
import com.panfleto.panfleto.entities.Product;
import com.panfleto.panfleto.services.market.MarketService;
import com.panfleto.panfleto.services.product.ProductService;
import com.panfleto.panfleto.services.s3.S3Service;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    final ProductService productService;
    final S3Service s3;

    public ProductController(ProductService productService, MarketService marketService, S3Service s3) {
        this.productService = productService;
        this.s3 = s3;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    // TODO refactoring all controller classes and make an entity constructor that convert dto to entity
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto object, MultipartFile file) {
        Product product = new Product();
        String url = s3.uploadObject("supermarket-images", object.getName(), file);

        product.setName(object.getName());
        product.setPrice(object.getPrice());
        product.setCategories(object.getCategories());
        product.setImgUrl(url);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestParam JSONObject object, @RequestParam MultipartFile file) {
        System.out.println(object.getJSONArray("categories"));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.updateProduct(id, object, file));
    }

    @GetMapping("/title={title}")
    public ResponseEntity<List<Product>> getByName(@PathVariable String title) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(title));
    }
}
