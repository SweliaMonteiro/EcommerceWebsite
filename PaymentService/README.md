# Payment Service

## Features
1. Implemented the following functionalities:
    - `Initiate Payment`: Allows a user to initiate the payment for the order. Payment can be done through Card, Online Transaction or Cash on Delivery.
    - `Track Payment`: Allows a user to track the status of the payment using the paymentId and payment mode.
2. Used Spring Data JPA to connect to MySQL DB and perform the operations.
3. For each API, Http status codes are returned to indicate the status of the request.
4. Implemented the exception handling for the following scenarios:
    - If the Oder with the given orderId is not found.
    - If the Payment with the given paymentId is not found.
    - If the Payment Status sent by the user is invalid.
5. Added Exception Handler to handle the exceptions globally.
6. Total amount is calculated for each order based on the number of products and their quantity in the cart.
7. Used OrderStatus enum to represent the status of the order, PaymentStatus enum to represent the status of the payment and PaymentMode enum to represent the mode of payment.
8. Implemented the `ONLINE TRANSACTION` payment mode using the `Razorpay` payment gateway which generates the payment link for the given order using Razarpay API.
9. Implemented the `CARD` payment mode for the given order.
10. Implemented the `CASH ON DELIVERY` payment mode which allows the user to pay the amount in cash when the order is delivered.
