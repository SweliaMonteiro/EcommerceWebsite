package com.ecommerce.orderservice.repositories;

import com.ecommerce.orderservice.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Override
    Optional<CartItem> findById(Long id);

}
