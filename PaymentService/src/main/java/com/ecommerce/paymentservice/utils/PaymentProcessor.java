package com.ecommerce.paymentservice.utils;

import com.ecommerce.paymentservice.models.CardPayment;
import com.ecommerce.paymentservice.models.OnlineTransactionPayment;
import com.ecommerce.paymentservice.models.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Component
public class PaymentProcessor {

    @Autowired
    private RazorpayClient razorpayClient;


    // This method is used to generate the payment link for the given order
    public OnlineTransactionPayment generateOnlineTransactionPaymentLink(Order order) throws RazorpayException {
        // Create json request object to create payment link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", order.getTotalAmount());  // Total amount to be paid
        paymentLinkRequest.put("currency", "INR");  // Currency of the payment
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(60);  // Set payment link expiration time to 60 minutes from now
        paymentLinkRequest.put("expire_by", expirationTime.atZone(ZoneId.systemDefault()).toEpochSecond());  // Convert the expiration time to epoch seconds
        paymentLinkRequest.put("reference_id", order.getId().toString());  // Reference id or receipt number of the payment
        paymentLinkRequest.put("description", "Payment for order no " + order.getId().toString());  // Description of the payment

        // Create a customer json object, set the details in json request object
        JSONObject customer = new JSONObject();
        customer.put("name", order.getUser().getName());
        customer.put("email", order.getUser().getEmail());
        paymentLinkRequest.put("customer", customer);

        // Notify the customer about the payment link using SMS and email, set the details in json request object
        JSONObject notify = new JSONObject();
        notify.put("sms", false);  // False means Razorpay will not handle sending SMS
        notify.put("email", true);  // True means Razorpay will handle sending email
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("reminder_enable", true);  // Used to send reminders for the payment link

        // Redirect to this URL once customers completes the payment
        paymentLinkRequest.put("callback_url", "https://Ecommerce.com/order/track/"+order.getId().toString());
        paymentLinkRequest.put("callback_method", "get");  // Make a GET request to the callback url

        // Create a payment link using all the parameters passed in the json request object
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        OnlineTransactionPayment onlineTransactionPayment = new OnlineTransactionPayment();
        onlineTransactionPayment.setOnlineTransactionPaymentId(payment.get("id"));
        onlineTransactionPayment.setPaymentLink(payment.get("short_url"));
        onlineTransactionPayment.setOrder(order);
        return onlineTransactionPayment;
    }


    // This method is used to generate the card payment for the given order
    public CardPayment generateCardPayment(Order order, String cardNumber, String cardHolderName, String cardExpiryDate) {
        CardPayment cardPayment = new CardPayment();
        cardPayment.setCardNumber(cardNumber);
        cardPayment.setCardHolderName(cardHolderName);
        cardPayment.setCardExpiryDate(cardExpiryDate);
        cardPayment.setOrder(order);
        return cardPayment;
    }

}
