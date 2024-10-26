package com.example.services;

import com.example.exceptions.CategoryNotFoundException;
import com.example.exceptions.NoProductsException;
import com.example.exceptions.ProductNotFoundException;
import com.example.models.Category;
import com.example.models.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ProductElasticSearchRepository;
import com.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

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
        // Get the product with the given ID from the database, if the product is not found, throw ProductNotFoundException
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with Id " + id + " does not exists");
        }
        return optionalProduct.get();
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
        // Create a Pageable object with the given page number, page size and sorting in ascending order by the given field
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        // Get all products whose category matches the given category using ElasticSearch
        Page<Product> products = productElasticSearchRepository.findAllByCategory(optionalCategory.get(), pageable);
        // If no products are found, throw NoProductsException
        if (products.isEmpty()) {
            throw new NoProductsException("No Products Found in Category " + category);
        }
        return products;
    }


    public List<Category> getAllCategories() {
        // Get all categories from the database
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

}
