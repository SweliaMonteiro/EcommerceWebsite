package com.example.orderservice.repositories;

import com.example.orderservice.models.Order;
import com.example.orderservice.models.User;
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
