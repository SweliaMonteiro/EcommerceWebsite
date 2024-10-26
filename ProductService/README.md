# Product Service

## Features
1. Implemented the following functionalities:
   - `Get All Products`: Returns a list of all the products available in the store as paginated list of products.
   - `Get Product By Id`: Returns the product with the given product id.
   - `Search Proucts By Name`: Returns a paginated list of products that matches or contains the provided name.
   - `Search Proucts By Category`: Returns a paginated list of products that belongs to the provided category.
   - `Get All Categories`: Returns a list of all the categories available in the store.
2. Added ResponseStatus annotation to return the appropriate HTTP status codes for each scenario.
3. Used Spring Data JPA to connect to MySQL DB and perform the operations. 
4. Implemented the search functionality using `Spring Data Elasticsearch` for optimal retrieval of products. 
5. Implemented pagination and sorting using `Pageable` interfaces. 
6. Implemented the exception handling for the following scenarios:
   - If the product with the given productId is not found.
   - If the category with the given category is not found.
   - If no products are found for the given search criteria.
7. Added Exception Handler to handle the exceptions globally.
