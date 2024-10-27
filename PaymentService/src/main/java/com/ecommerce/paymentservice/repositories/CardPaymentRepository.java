package com.ecommerce.paymentservice.repositories;

import com.ecommerce.paymentservice.models.CardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Long> {

    @Override
    <S extends CardPayment> S save(S entity);

    @Override
    Optional<CardPayment> findById(Long id);

}
