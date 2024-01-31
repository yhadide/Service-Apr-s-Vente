# Project Title

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Security Considerations](#security-considerations)
- [Setup and Configuration](#setup-and-configuration)
- [Contributors](#contributors)
- [License](#license)

## Overview

This project, developed primarily in French as it aligns with the language of our academic studies, is a collaborative effort aimed at implementing a robust system for managing client calls and technical interventions. Leveraging Spring Boot, Spring Security, and Thymeleaf, we've created a comprehensive application tailored to address the specific needs of our scenario.

## Features

- **Call and Dossiers (Case Files) Management:** Efficiently handle client calls, track their status, and ensure timely resolution.
- **Technical Interventions:** Manage technical interventions seamlessly, from opening to closing of cases.
- **User Roles:** Implement a role-based access control system to ensure secure access and data protection.
- **Multi-Language Support:** Designed with French as the primary language to suit our academic context.

## Technologies Used

- **Spring Boot:** Facilitates rapid application development with its convention-over-configuration approach.
- **Spring Security:** Provides robust authentication and authorization mechanisms to safeguard our application.
- **Thymeleaf:** Enables server-side rendering of HTML templates with seamless integration with Spring Boot.
- **MySQL:** Utilized as the relational database management system to store and manage application data.

## Project Structure

The project consists of several key components:

- **Database Schema:** Defines the structure of our database tables, including entities such as clients, articles, calls, technicians, and more.
- **Java Backend:** Implements the business logic and application functionalities using Spring Boot.
- **Frontend Templates:** Utilizes Thymeleaf for server-side rendering of HTML templates for user interfaces.
- **Security Configuration:** Leverages Spring Security for authentication and authorization to ensure secure access to the application.

## Security Considerations

Spring Security plays a pivotal role in ensuring the security of our application. By providing robust authentication and authorization mechanisms, it safeguards sensitive data and restricts access based on user roles. With Spring Security, we can:

- **Authenticate Users:** Verify the identity of users accessing the system through various authentication methods.
- **Authorize Access:** Control access to different parts of the application based on user roles, ensuring that each user has appropriate permissions.
- **Protect Resources:** Secure endpoints and sensitive data to prevent unauthorized access and maintain data integrity.
- **Implement Security Policies:** Enforce security policies such as password hashing, session management, and CSRF protection to mitigate common security threats.

## Setup and Configuration

To set up and configure the project locally, follow these steps:

1. **Clone the Repository:**

git clone https://github.com/yhadide/sav

2. **Database Configuration:**
- Install MySQL(Wampsever or else) and create a database schema.
- Update the `application.properties` file with your database credentials.

3. **Build and Run the Application:**

cd project-name
./mvnw spring-boot:run

4. **Access the Application:**
Once the application is running, access it at `http://localhost:8081` in your web browser.

## Contributors

This project was made possible through the collaborative efforts of the following contributors:

- HADIDE YOUNESS(https://github.com/yhadide)
- TARIK NIZAR(https://github.com/nizart01)

## License

This project is licensed under the [MIT License](LICENSE).
