package org.fxclub.qa.cucumber4.examples;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features"},
        plugin = {"org.fxclub.qa.cucumber4.ConsoleOutputPlugin", "null_summary"},
        monochrome = false,
        glue = {"org.fxclub.qa.cucumber4.examples.steps"})
public class CucumberTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
