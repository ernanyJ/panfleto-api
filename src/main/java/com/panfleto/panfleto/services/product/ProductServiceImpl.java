package com.panfleto.panfleto.services.product;

import com.panfleto.panfleto.entities.Product;
import com.panfleto.panfleto.repositories.ProductRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, JSONObject object, MultipartFile file) {
        Product oldProduct = productRepository.findById(id).orElse(null);

        Product product = new Product(object);

        product.setId(oldProduct.getId());
        return productRepository.save(product);

    }

    @Override
    public Product createProduct(Product product) {
        product.setNormalizedName(product.getName().toLowerCase().replaceAll("\\s+", ""));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductByName(String title) {
        return productRepository.findByNameIsContainingIgnoreCase(title);
    }

    @Override
    public Optional<Product> searchSimilarProducts(String title) {
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        List<Product> products = productRepository.findAll();
        for(Product p : products){
            if(levenshteinDistance.apply(p.getNormalizedName(), title) < 3){
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }


}
