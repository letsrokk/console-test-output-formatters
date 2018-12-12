package org.fxclub.qa.cucumber4.examples.steps;

import cucumber.api.java.en.When;
import org.fxclub.qa.cucumber4.TestContext;

/**
 * Created by majer-dy on 19/04/2017.
 */
public class ContextSteps1 {

    @When("^when in first class we create Helper instance$")
    public void whe_run_the_scenario() throws Throwable {
        HelperClass helper = new HelperClass();
        TestContext.set(helper);
    }

    @When("^when in first class we create object$")
    public void when_we_create_object() throws Throwable {
        HelperClass helper = new HelperClass();
        TestContext.set("object", helper);
    }

}
