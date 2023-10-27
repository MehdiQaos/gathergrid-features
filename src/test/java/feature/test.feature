Feature: Adding Two Numbers

  Scenario Outline: Add two numbers
    When I have the first number <number1>
    And I have the second number <number2>
    When I add the numbers
    Then the result should be <result> on the screen

    Examples:
      | number1 | number2 | result |
      | 5       | 3       | 8      |
      | 10      | 20      | 30     |
      | -2      | 2       | 0      |
