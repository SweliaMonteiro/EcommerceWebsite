package com.example.productservice.services;

import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.exceptions.NoProductsException;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.repositories.ProductElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductElasticSearchRepository productElasticSearchRepository;


    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) throws NoProductsException {
        // Create a Pageable object with the given page number, page size and sorting order
        // Sort the products in ascending order by the given field and then in descending order by the price field
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending().and(Sort.by("price").descending()));
        // Get all products from the database using the ProductRepository, if no products are found, throw NoProductsException
        Page<Product> products = productRepository.findAll(pageable);
        if (products.isEmpty()) {
            throw new NoProductsException("No Products Found");
        }
        return products;
    }


    public Product getProductById(Long id) throws ProductNotFoundException {
        // Return product if stored with the given id in Redis. It is stored in ProductId map with key as "Product_"+id
        Product product = (Product) redisTemplate.opsForHash().get("ProductId", "Product_"+id);
        if(product != null) {
            return product;
        }
        // Else fetch the product with the given id from the database, if the product is not found, throw ProductNotFoundException
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with Id " + id + " does not exists");
        }
        product = optionalProduct.get();
        // Store the product as a value in ProductId map of Redis with key as "Product_"+id
        redisTemplate.opsForHash().put("ProductId", "Product_"+id, product);
        return product;
    }


    public Page<Product> getProductsByName(String name, int pageNumber, int pageSize, String sortBy) throws NoProductsException {
        // Create a Pageable object with the given page number, page size and sorting in ascending order by the given field
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        // Get all products whose name matches or contains the given name using ElasticSearch
        Page<Product> products = productElasticSearchRepository.findByNameContaining(name, pageable);
        // If no products are found, throw NoProductsException
        if (products.isEmpty()) {
            throw new NoProductsException("No Products Found with Name " + name);
        }
        return products;
    }


    public Page<Product> getAllProductsByCategory(String category, int pageNumber, int pageSize, String sortBy) throws CategoryNotFoundException, NoProductsException {
        // Get the category with the given name, if the category is not found, throw CategoryNotFoundException
        Optional<Category> optionalCategory = categoryRepository.findByName(category);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with name " + category + " does not exists");
        }
        // Return product list if stored with the given category in Redis. It is stored in ProductCategory map with key as "Category_"+category
        Page<Product> products = (Page<Product>) redisTemplate.opsForHash().get("ProductCategory", "Category_"+category);
        if(products != null) {
            return products;
        }
        // Else create a Pageable object with the given page number, page size and sorting in ascending order by the given field
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        // Get all products whose category matches the given category using ElasticSearch
        products = productElasticSearchRepository.findAllByCategory(optionalCategory.get(), pageable);
        // If no products are found, throw NoProductsException
        if (products.isEmpty()) {
            throw new NoProductsException("No Products Found with Category " + category);
        }
        // Store the list of product as a value in ProductCategory map of Redis with key as "Category_"+category
        redisTemplate.opsForHash().put("ProductCategory", "Category_"+category, products);
        return products;
    }


    public List<Category> getAllCategories() {
        // Get all categories from the database
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

}
