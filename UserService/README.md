# User Service

## Features
1. Implemented the following functionalities:
   - `Registration`: Allows a User to register with the system. The User should provide a name, email id and password.
   - `Login`: Allows a User to login to the system. The User should provide an email id and password. It validates the User's credentials and returns a token. The token is generated using RandomAlphanumericStringGenerator and is valid for 30 days.
   - `Logout`: Allows a User to logout of the system. Takes in the User's token and invalidates it.
   - `Validate Token`: Allows the validation of the User token. It takes token as a input and returns the User's data if the token is valid.
   - `Update User`: Allows the User to update their details. The User can update their name, email id and password.
2. The User's Password is hashed using Bcrypt algorithm before storing in the database to ensure security. 
3. For each API, Http status codes are returned to indicate the status of the request. 
4. Implemented the exception handling for the following scenarios:
   - If the User with the given userId is not found.
   - If the User with the given email already exists.
   - If the Password provided by the User is incorrect.
   - If the Token provided is invalid.
5. Added Exception Handler to handle the exceptions globally. 
6. Used Spring Data JPA to connect to MySQL DB and perform the operations.
7. Added Security Configuration to permit access to only `/user/*` endpoints.
8. Used Kafka to send the User details to the Notification Service whenever a User registers, logs in and updates their profile to notify the User.
