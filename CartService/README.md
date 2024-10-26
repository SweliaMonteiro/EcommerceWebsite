# Cart Service

## Features
1. Implemented the following functionalities:
   - `Add Product to Cart`: Allows a user to add a product to the cart. If the product is already present in the cart, the quantity is updated. If the product is not present, a new entry is added to the cart.
   - `View All Products in Cart`: Allows a user to view all the products in the cart.
2. Used Spring Data JPA to connect to MySQL DB and perform the operations.
3. For each API, Http status codes are returned to indicate the status of the request. 
4. Implemented the exception handling for the following scenarios:
   - If the User with the given userId is not found.
   - If the Product with the given productId is not found.
5. Added Exception Handler to handle the exceptions globally.
