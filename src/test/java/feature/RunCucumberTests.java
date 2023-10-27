package feature;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", // Directory where your feature files are located
        glue = "step_definitions") // Package where your step definitions are located

public class RunCucumberTests {
}

