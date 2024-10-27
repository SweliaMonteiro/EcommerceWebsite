package com.ecommerce.orderservice.repositories;

import com.ecommerce.orderservice.models.User;
import com.ecommerce.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Optional<Order> findById(Long id);

    @Override
    <S extends Order> S save(S entity);

    List<Order> findAllByUser(User user);

}
