package com.mobile.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"com.mobile"},
        features = "src/test/resources",
        plugin = { "pretty", "html:target/cucumber-pretty", "json:target/jsonReports/cucumber.json"})
public class RunCucumberIT {

}