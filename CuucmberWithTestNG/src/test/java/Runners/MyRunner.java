package Runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "/Users/Prateek/Downloads/CuucmberWithTestNG/CuucmberWithTestNG/src/test/java/Features/ExcellentableSearch.feature", 
glue = "StepDefination",
plugin = {
		"pretty", "html:target/cucumber.html" })
public class MyRunner extends AbstractTestNGCucumberTests{

}
