package com.ecommerce.cartservice.services;

import com.ecommerce.cartservice.exceptions.ProductNotFoundException;
import com.ecommerce.cartservice.exceptions.UserNotFoundException;
import com.ecommerce.cartservice.models.User;
import com.ecommerce.cartservice.repositories.CartRepository;
import com.ecommerce.cartservice.repositories.ProductRepository;
import com.ecommerce.cartservice.repositories.UserRepository;
import com.ecommerce.cartservice.models.Cart;
import com.ecommerce.cartservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public Cart addToCart(Long userId, Long productId) throws UserNotFoundException, ProductNotFoundException {
        // Check if the user exists in the database, if not throw UserNotFoundException
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " does not exists");
        }
        User user = optionalUser.get();

        // Check if the product exists in the database, if not throw ProductNotFoundException
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + productId + " does not exists");
        }
        Product product = optionalProduct.get();

        // Check if the product is already in the cart
        Optional<Cart> optionalCart = cartRepository.findAllByUserAndProduct(user, product);
        Cart cart = null;
        // If the product is not in the cart, create a new cart object and save to database
        if (optionalCart.isEmpty()) {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(1);
        }
        // If the product is already in the cart, increment the quantity by 1
        else {
            cart = optionalCart.get();
            cart.setQuantity(cart.getQuantity()+1);
        }
        cart = cartRepository.save(cart);
        return cart;
    }


    public List<Cart> viewCart(Long userId) throws UserNotFoundException {
        // Check if the user exists in the database, if not throw UserNotFoundException
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " does not exists");
        }
        User user = optionalUser.get();
        // Return all the products in the cart for the user
        return cartRepository.findAllByUser(user);
    }

}