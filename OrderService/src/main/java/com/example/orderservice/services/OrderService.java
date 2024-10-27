package com.example.orderservice.services;

import com.example.orderservice.exceptions.*;
import com.example.orderservice.models.*;
import com.example.orderservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    public Order checkout(Long userId, List<Long> cartItemIds, String deliveryAddress, String paymentMode) throws UserNotFoundException, CartItemNotFoundException, InvalidPaymentModeException {
        // Check if the user exists in the database, if not throw UserNotFoundException
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with Id " + userId + " does not exits");
        }
        User user = userOptional.get();

        // Calculate the total amount of the order and create list of cart items
        double totalAmount = 0;
        List<CartItem> cartItems = new ArrayList<>();
        for (Long cartIemId : cartItemIds) {
            // Check if the cart item exists in the database, if not throw CartItemNotFoundException
            Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartIemId);
            if (cartItemOptional.isEmpty()) {
                throw new CartItemNotFoundException("Cart Item with Id " + cartIemId + " does not exits");
            }
            CartItem cartItem = cartItemOptional.get();
            cartItems.add(cartItem);
            Product product = cartItem.getProduct();
            totalAmount += product.getPrice() * cartItem.getQuantity();
        }
        // Check if payment mode is valid, if not throw InvalidPaymentModeException
        if (paymentMode!=null && !paymentMode.isEmpty()) {
            try {
                PaymentMode.valueOf(paymentMode);
            }
            catch (IllegalArgumentException e) {
                throw new InvalidPaymentModeException("Invalid Payment Mode: " + paymentMode);
            }
        }

        // Create a new order object and save to database, set the order status and payment status to "Pending" as the order is not yet processed
        Order order = new Order();
        order.setUser(user);
        order.setCartItems(cartItems);
        order.setTotalAmount(totalAmount);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentMode(PaymentMode.valueOf(paymentMode));
        order.setPaymentStatus(PaymentStatus.PENDING);
        return orderRepository.save(order);
    }


    public List<Order> getOrderHistory(Long userId) throws UserNotFoundException {
        // Check if the user exists in the database, if not throw UserNotFoundException
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with Id " + userId + " does not exits");
        }
        User user = userOptional.get();
        // Return all the orders for the user
        return orderRepository.findAllByUser(user);
    }


    public Order trackOrder(Long orderId) throws OrderNotFoundException {
        // Check if the order exists in the database, if not throw OrderNotFoundException
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order with Id " + orderId + " does not exits");
        }
        // Return the order details
        return orderOptional.get();
    }


    public Order updateOrder(Long orderId, String orderStatus, String paymentStatus) throws OrderNotFoundException, InvalidOrderStatusException, InvalidPaymentStatusException{
        // Check if the order exists in the database, if not throw OrderNotFoundException
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order with Id " + orderId + " does not exits");
        }
        Order order = orderOptional.get();

        // Check if order status is valid, if not throw InvalidOrderStatusException
        if (orderStatus!=null && !orderStatus.isEmpty()) {
            try {
                order.setOrderStatus(OrderStatus.valueOf(orderStatus));
            }
            catch (IllegalArgumentException e) {
                throw new InvalidOrderStatusException("Invalid Order Status: " + orderStatus);
            }
        }
        // Check if payment status is valid, if not throw InvalidPaymentStatusException
        if (paymentStatus!=null && !paymentStatus.isEmpty()) {
            try {
                order.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
            }
            catch (IllegalArgumentException e) {
                throw new InvalidPaymentStatusException("Invalid Payment Status: " + paymentStatus);
            }
        }
        // Save the updated order to the database
        return orderRepository.save(order);
    }

}
