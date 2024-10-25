# User Service

## Features
1. `Registration`: Allows a User to register with the system. The User should provide a name, email id and password.
2. `Login`: Allows a User to login to the system. The User should provide an email id and password. It validates the User's credentials and returns a token. The token is generated using RandomAlphanumericStringGenerator and is valid for 30 days.
3. `Logout`: Allows a User to logout of the system. Takes in the User's token and invalidates it.
4. `Validate Token`: Allows the validation of the User token. It takes token as a input and returns the User's data if the token is valid.
5. `Update User`: Allows the User to update their details. The User can update their name, email id and password.
6. Password is hashed using Bcrypt algorithm before storing in the database to ensure security.
7. For each API, Http status codes are returned to indicate the status of the request.
