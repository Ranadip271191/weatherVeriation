package com.WeatherReporting.API.cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
/**
 * Sole puspose is to execute the steps specified on feature file and generate json report
 *
 * This class will run using junit
 *
 * @author Ranadip Bakshi
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/WeatherReporting/API/features/", glue = {"com.WeatherReporting.API.stepDefinations"}, tags = {""}, plugin = {"json:target/jsonReports/cucumber-report.json"})
public class TestRunner {

    }

