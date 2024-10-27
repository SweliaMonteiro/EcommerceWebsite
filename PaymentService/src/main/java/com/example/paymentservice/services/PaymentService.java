package com.example.paymentservice.services;

import com.example.paymentservice.dtos.*;
import com.example.paymentservice.exceptions.*;
import com.example.paymentservice.models.*;
import com.example.paymentservice.repositories.*;
import com.example.paymentservice.utils.PaymentProcessor;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.EnumSet;
import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    private PaymentProcessor paymentProcessor;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OnlineTransactionPaymentRepository onlineTransactionPaymentRepository;

    @Autowired
    private CardPaymentRepository cardPaymentRepository;


    public InitiatePaymentResponseDto initiatePayment(Long orderId, String cardNumber, String cardHolderName, String cardExpiryDate) throws OrderNotFoundException, RazorpayException, InvalidPaymentModeException {
        // Check if the order exists in the database, if not throw OrderNotFoundException
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order with Id " + orderId + " does not exits");
        }
        Order order = orderOptional.get();

        // If payment mode is online transaction, then generate the payment link and save to database
        if (order.getPaymentMode().equals(PaymentMode.ONLINE_TRANSACTION)) {
            OnlineTransactionPayment onlineTransactionPayment = onlineTransactionPaymentRepository.save(paymentProcessor.generateOnlineTransactionPaymentLink(order));
            InitiatePaymentResponseDto initiatePaymentResponseDto = new InitiatePaymentResponseDto();
            initiatePaymentResponseDto.setPaymentMode(order.getPaymentMode().name());
            initiatePaymentResponseDto.setPaymentStatus(PaymentStatus.PENDING.name());
            initiatePaymentResponseDto.setOnlinePaymentLink(onlineTransactionPayment.getPaymentLink());
            return initiatePaymentResponseDto;
        }
        // If payment mode is card, then generate the card payment and save to database
        else if (order.getPaymentMode().equals(PaymentMode.CARD)) {
            cardPaymentRepository.save(paymentProcessor.generateCardPayment(order, cardNumber, cardHolderName, cardExpiryDate));
            InitiatePaymentResponseDto initiatePaymentResponseDto = new InitiatePaymentResponseDto();
            initiatePaymentResponseDto.setPaymentMode(order.getPaymentMode().name());
            initiatePaymentResponseDto.setPaymentStatus(PaymentStatus.COMPLETED.name());
            return initiatePaymentResponseDto;
        }
        // If payment mode is cash, then return the payment mode and payment status of the order as no processing is required
        else if (order.getPaymentMode().equals(PaymentMode.CASH)) {
            InitiatePaymentResponseDto initiatePaymentResponseDto = new InitiatePaymentResponseDto();
            initiatePaymentResponseDto.setPaymentMode(order.getPaymentMode().name());
            initiatePaymentResponseDto.setPaymentStatus(order.getPaymentStatus().name());
            return initiatePaymentResponseDto;
        }
        // If payment mode is not valid, then throw InvalidPaymentModeException
        throw new InvalidPaymentModeException("Invalid Payment Mode: " + order.getPaymentMode().name());
    }


    public TrackPaymentResponseDto trackPayment(Long paymentId, String paymentMode) throws PaymentNotFoundException, InvalidPaymentModeException {
        // Check if the payment mode is valid, if not throw InvalidPaymentModeException
        if (EnumSet.allOf(PaymentMode.class).stream().noneMatch(paymentMode::equals)) {
            throw new InvalidPaymentModeException("Invalid Payment Mode: " + paymentMode);
        }

        // If payment mode is online transaction, then get the payment status from the database
        if (PaymentMode.valueOf(paymentMode).equals(PaymentMode.ONLINE_TRANSACTION)) {
            // Check if the payment exists in the database, if not throw PaymentNotFoundException
            Optional<OnlineTransactionPayment> onlineTransactionPaymentOptional = onlineTransactionPaymentRepository.findById(paymentId);
            if (onlineTransactionPaymentOptional.isEmpty()) {
                throw new PaymentNotFoundException("Payment with Id " + paymentId + " does not exits");
            }
            OnlineTransactionPayment onlineTransactionPayment = onlineTransactionPaymentOptional.get();
            TrackPaymentResponseDto trackPaymentResponseDto = new TrackPaymentResponseDto();
            trackPaymentResponseDto.setPaymentStatus(onlineTransactionPayment.getOrder().getPaymentStatus().name());
            return trackPaymentResponseDto;
        }
        // If payment mode is card, then get the payment status from the database
        else if (PaymentMode.valueOf(paymentMode).equals(PaymentMode.CARD)) {
            // Check if the payment exists in the database, if not throw PaymentNotFoundException
            Optional<CardPayment> cardPaymentOptional = cardPaymentRepository.findById(paymentId);
            if (cardPaymentOptional.isEmpty()) {
                throw new PaymentNotFoundException("Payment with Id " + paymentId + " does not exits");
            }
            CardPayment cardPayment = cardPaymentOptional.get();
            TrackPaymentResponseDto trackPaymentResponseDto = new TrackPaymentResponseDto();
            trackPaymentResponseDto.setPaymentStatus(cardPayment.getOrder().getPaymentStatus().name());
            return trackPaymentResponseDto;
        }
        // If payment mode is cash or not valid, then throw InvalidPaymentModeException
        throw new InvalidPaymentModeException("Invalid Payment Mode for Tracking: " + paymentMode);
    }

}
