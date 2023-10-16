package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "/Users/testvagrant/Desktop/contactlist_apiautomation_assignment/app/src/test/java/Features", glue = {"FeatureDefinitions"})
public class TestRunner {
}
