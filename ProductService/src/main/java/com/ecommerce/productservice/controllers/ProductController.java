package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.exceptions.CategoryNotFoundException;
import com.ecommerce.productservice.exceptions.NoProductsException;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    // This method is used to get all products from the database and throw an exception if there are no products
    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
                                                        @RequestParam(value="pageSize", defaultValue="5") int pageSize,
                                                        @RequestParam(value="sortBy", defaultValue="id") String sortBy) throws NoProductsException {
        Page<Product> productsPage = productService.getAllProducts(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }


    // This method is used to get a product by its ID and throws an exception if the product is not found
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // This method is used to get products whose name matches or contains the given name and throws an exception if no products are found
    @GetMapping("/{name}")
    public ResponseEntity<Page<Product>> getProductsByName(@PathVariable("name") String name,
                                                            @RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
                                                            @RequestParam(value="pageSize", defaultValue="5") int pageSize,
                                                            @RequestParam(value="sortBy", defaultValue="id") String sortBy) throws NoProductsException {
        Page<Product> productsPage = productService.getProductsByName(name, pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }


    // This method is used to get all products by category and throws an exception if the category is not found or if there are no products in the category
    @GetMapping("/{category}")
    public ResponseEntity<Page<Product>> getAllProductsByCategory(@PathVariable("category") String category,
                                                                  @RequestParam(value="pageNumber", defaultValue="0") int pageNumber,
                                                                  @RequestParam(value="pageSize", defaultValue="5") int pageSize,
                                                                  @RequestParam(value="sortBy", defaultValue="id") String sortBy) throws CategoryNotFoundException, NoProductsException {
        Page<Product> productsPage = productService.getAllProductsByCategory(category, pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }


    // This method is used to get all categories
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
