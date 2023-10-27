package feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNumber {
    private int number1;
    private int number2;
    private int result;

    @Given("I have the first number (\\d+)")
    public void i_have_the_first_number(int number1) {
        this.number1 = number1;
    }

    @Given("I have the second number (\\d+)")
    public void i_have_the_second_number(int number2) {
        this.number2 = number2;
    }

    @When("I add the numbers")
    public void i_add_the_numbers() {
        result = number1 + number2;
    }

    @Then("the result should be (\\d+) on the screen")
    public void the_result_should_be_on_the_screen(int expectedResult) {
        assert(result == expectedResult);
    }
}
