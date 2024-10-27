package com.example.paymentservice.repositories;

import com.example.paymentservice.models.OnlineTransactionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface OnlineTransactionPaymentRepository extends JpaRepository<OnlineTransactionPayment, Long> {

    @Override
    <S extends OnlineTransactionPayment> S save(S entity);

    @Override
    Optional<OnlineTransactionPayment> findById(Long id);

}
