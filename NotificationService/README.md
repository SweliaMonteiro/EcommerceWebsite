# Notification Service

## Features
1. Implemented notification for following scenarios:
   - `User Register`: Sends a welcome email to the user after successful registration.
   - `User LogIn`: Sends a welcome back email to the user after successful login.
   - `User Profile Update`: Sends an email to the user after successful updation of the profile.
   - `User Order Update`: Sends an email to the user after the status of the order is updated.
2. Implemented the Kafka functionality using `Spring Kafka` inorder to receive the messages from the Kafka Topic.
3. Used `JavaMail API` to send the email notification to the user.
4. Created KafkaListener methods to listen to the Kafka Topics and send an email notification to the user using the details received from the Kafka Topic.
5. Configured email subject and body for each type of email notification in the application.properties file.
6. Used Logger to log the messages when the email is successfully sent to the user or if there is any error while sending the email.
