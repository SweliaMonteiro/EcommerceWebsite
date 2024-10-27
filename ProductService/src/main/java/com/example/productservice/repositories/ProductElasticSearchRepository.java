package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, Long> {

    Page<Product> findByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

}
