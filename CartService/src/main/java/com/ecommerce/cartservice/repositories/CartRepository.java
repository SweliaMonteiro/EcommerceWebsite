package com.ecommerce.cartservice.repositories;

import com.ecommerce.cartservice.models.Cart;
import com.ecommerce.cartservice.models.Product;
import com.ecommerce.cartservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Override
    <S extends Cart> S save(S entity);

    List<Cart> findAllByUser(User user);

    Optional<Cart> findAllByUserAndProduct(User user, Product product);

}
