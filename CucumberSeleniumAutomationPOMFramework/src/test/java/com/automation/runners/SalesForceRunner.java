package com.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/salesForce.feature"},
		glue= {"com.automation.steps"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty",
				"html:target/cucumber-reports",
				"json:target/cucumber.json"
				
		}
		
		)
public class SalesForceRunner extends AbstractTestNGCucumberTests {
	

}
