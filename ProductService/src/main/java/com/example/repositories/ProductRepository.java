package com.example.repositories;

import com.example.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    Optional<Product> findById(Long aLong);

}
