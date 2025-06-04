===================================================================
===================================================================

Library Management REST API - Project Summary
=============================================
===================================================================


Overview
=========

This Spring Boot application provides a simple library system to manage books and borrowers. It supports registering books and borrowers, borrowing and returning books, and listing available books.


Features Implemented
=====================

Book Management
----------------
- Register a new book (supports multiple copies per ISBN)
- Retrieve a list of all books
- Books are uniquely identified by `id`, while `isbn` enforces consistency of title and author

Borrower Management
--------------------
- Register a new borrower with name and email
- Enforced unique email constraint

Borrowing Logic
----------------
- Borrow a book by book ID and borrower ID
- Return a previously borrowed book
- Ensures:
  - A single borrower per book at a time
  - Validations for book and borrower existence
  - No re-borrowing of already borrowed books
  
  
Technologies Used
==================

Language : Java 17
Framework : Spring Boot
Build Tool : Maven
Database : H2 (in-memory, for dev/testing)
ORM : Spring Data JPA
Testing : JUnit 5, Mockito, Karate(api-testing)
Code Coverage : JaCoCo
Documentation : Springdoc OpenAPI (Swagger UI)


Validations & Exception Handling
=================================
- All DTOs use `javax.validation` annotations (e.g. `@NotNull`, `@Email`)
- Global exception handler using `@ControllerAdvice` and `@ExceptionHandler`
- Consistent error/response model via `ApiResponse<T>`

Testing
=======
- Unit Tests: Written for controller, service, and repository layers
- Integration Tests: Karate test suite for end-to-end API testing
- Code Coverage: JaCoCo integrated, HTML report generated in `target/site/jacoco/index.html`

Documentation
=============
- OpenAPI (Swagger): Auto-generated documentation at `/swagger-ui.html` or `/v3/api-docs`
- Postman Collection: Attached `library-api.postman_collection.json` (with sample requests)
- Curl Examples: Provided inline and in `README.md`
- API Responses: Wrapped in generic `ApiResponse<T>` format


How to Run the Project Locally
==============================

Build and run with Maven:
    mvn clean spring-boot:run

API Documentation:
    http://localhost:8080/swagger-ui.html

H2 Console (for dev):
    http://localhost:8080/h2-console
Environment Configuration
- Uses `application.yml` for default and profile-specific configs
- Profiles like `dev`, `test` can be added via `spring.profiles.active`
