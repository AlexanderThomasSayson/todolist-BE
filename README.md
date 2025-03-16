# To-Do List - Spring Boot Project (Backend)

## Overview
This is a **Spring Boot** backend for a To-Do List application. The project follows a **Role-Based Access Control (RBAC)** model, providing different access levels for Admin and User roles. It leverages **Spring Security**, **JPA**, and **OpenAPI** for API documentation.

## Technologies Used
- **Java 21**
- **Spring Boot**
- **Spring Security**
- **JPA (Java Persistence API)**
- **MySQL**
- **Springdoc OpenAPI (springdoc-openapi-starter-webmvc-ui) 2.3.0**

## Features
- User authentication and authorization with **Spring Security**
- **RBAC (Role-Based Access Control)** for Admin and User
- Secure REST API endpoints
- CRUD operations for managing to-do items
- OpenAPI documentation for API exploration

## Setup Instructions
### Prerequisites
Ensure you have the following installed:
- **Java 21**
- **Maven**
- **MySQL** (for database storage)

### Installation Steps
1. **Clone the repository:**
   ```sh
   git clone https://github.com/AlexanderThomasSayson/todolist-BE.git
   cd todolist-BE
   ```
2. **Configure the database**
    - Update `application.properties` or `application.yml` with database details (contact owner).
   

3. **Build and run the project:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access API Documentation:**
    - Open `http://localhost:8084/swagger-ui.html` in your browser to explore the available endpoints.

## API Endpoints (Examples)
### Authentication
- `POST /api/v1/auth/login` - Authenticate user and get JWT token
- `POST /api/v1/auth/register` - Register a new user

### To-Do Management
- `GET /api/v1/todos/getById/{id}` - Retrieve a to-do item (Admin only)
- `POST /api/v1/todos/create` - Create a new to-do item (Admin only)
- `PUT /api/v1/todos/update/{id}` - Update a to-do item (Admin only)
- `DELETE /api/v1/todos/soft-delete/{id}` - Soft delete a to-do item (Admin only)
- `PUT /api/v1/todos/complete/{id}` - Complete a to-do item (User/Admin)

## Security & Authorization
- Users must authenticate via **JWT tokens**.
- **Admin** can manage all users and to-do items.
- **Users** can only manage their own to-do items.

## Contribution
Contributions are welcome! Feel free to submit a pull request.


