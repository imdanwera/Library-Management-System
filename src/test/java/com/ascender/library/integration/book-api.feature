Feature: Book API Integration Tests

  Background:
    * url 'http://localhost:8080/api/books'

  Scenario: Register a new book
    Given request { isbn: '1234567890', title: 'Clean Code', author: 'Robert C. Martin' }
    When method POST
    Then status 201
    And match response.success == true
    And match response.data.title == 'Clean Code'

  Scenario: Get list of books
    When method GET
    Then status 200
    And match response.success == true
    And match response.data == '#[]'
