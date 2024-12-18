# Order Service

## Features
1. Implemented the following functionalities:
   - `Order Processing`: Allows a user to place an order with the products in the cart. Order and payment status is set to PENDING as the order is not yet processed.
   - `Order History`: Allows a user to view all the orders placed by them.
   - `Track Order`: Allows a user to track the status of the order using the orderId.
   - `Update Order`: Allows to update the status of the order and payment using the orderId. Also sends notification to the user about the status update.
2. Used Spring Data JPA to connect to MySQL DB and perform the operations.
3. For each API, Http status codes are returned to indicate the status of the request. 
4. Implemented the exception handling for the following scenarios:
   - If the User with the given userId is not found.
   - If the Oder with the given orderId is not found.
   - If the CartItem with the given cartItemId is not found.
   - If the Order Status sent by the user is invalid.
   - If the Payment Status sent by the user is invalid.
5. Added Exception Handler to handle the exceptions globally.
6. Total amount is calculated for each order based on the number of products and their quantity in the cart.
7. Used OrderStatus enum to represent the status of the order, PaymentStatus enum to represent the status of the payment and PaymentMode enum to represent the mode of payment.
8. Used Kafka to send the order details to the Notification Service whenever order status is updated to notify the user about the order status.
