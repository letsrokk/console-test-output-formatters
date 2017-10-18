package org.fclub.qa.cucumber2.examples.steps;

import cucumber.api.java.en.Then;
import org.fxclub.qa.cucumber2.TestContext;
import org.junit.Assert;

/**
 * Created by majer-dy on 19/04/2017.
 */
public class ContextSteps2 {

    @Then("^we can use same instance in different class$")
    public void scenario_name_shuld_be_complete() throws Throwable {
        HelperClass helper = TestContext.get(HelperClass.class);
        Assert.assertNotNull(helper);
    }

    @Then("^we can use existing object$")
    public void we_can_use_object() throws Throwable {
        Assert.assertFalse(
                TestContext.get("object", HelperClass.class).someField
        );
    }

}
