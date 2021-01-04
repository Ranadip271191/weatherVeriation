package com.WeatherReporting.API.stepDefinations;

import com.WeatherReporting.comparator.CustomException;
import com.WeatherReporting.library;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import java.io.IOException;
/**
 *  Sole purpose is to fetch data from results.properties and validate the veriation of 2
 * @author Ranadip Bakshi
 */


public class CompareVeriation {

    public static String city;
    String API_celcius;
    String API_fahrenheit;

    String UI_celcius;
    String UI_fahrenheit;

    @Rule
    ErrorCollector ec = new ErrorCollector();

    @Given("User get {string}")
    public void user_get(String cityname) {
       city = cityname;
    }

    @When("User get UI and API data")
    public void user_get_UI_and_API_data() throws IOException {
        /**
         * will get the from results.properties file in specific format
         */
        String resultfilepath = "src/test/java/com/WeatherReporting/results.properties";
        String API_celcius_value = city+"_API_celcius";
        String API_fahrenheit_value = city+"_API_fahrenheit";

        String UI_celcius_value = city+"_UI_celcius";
        String UI_fahrenheit_value = city+"_UI_fahrenheit";

        API_celcius = library.getValuefromproperty(API_celcius_value,resultfilepath);
        API_fahrenheit = library.getValuefromproperty(API_fahrenheit_value,resultfilepath);

        UI_celcius = library.getValuefromproperty(UI_celcius_value,resultfilepath);
        UI_fahrenheit = library.getValuefromproperty(UI_fahrenheit_value,resultfilepath);


    }

    @Then("User compare the veriation")
    public void user_compare_the_veriation() throws CustomException {

        double api_celcius = covertStringtodouble(API_celcius);
        double api_fahrenheit = covertStringtodouble(API_fahrenheit);
        double ui_celcius = covertStringtodouble(UI_celcius);
        double ui_fahrenheit = covertStringtodouble(UI_fahrenheit);

        boolean celciusVeriation = compare_variation(api_celcius,ui_celcius);
        boolean fahrenheitVeriation = compare_variation(api_fahrenheit,ui_fahrenheit);


        try {
            Assert.assertTrue("Celcius veriation of " + city + " did not match", celciusVeriation);
            Assert.assertTrue("fahrenheit veriation of " + city + " did not match", fahrenheitVeriation);
        }catch (Exception e){
            ec.addError(e);
        }
    }

    public static double covertStringtodouble(String data)
    {
        return Double.parseDouble(data);
    }

    private static boolean compare_variation(double s1, double s2)
    {
        /*
         @return true if variation between s1 and s2 is less then 2
         else return false.
         */
        boolean flag = false;
        double variartion_value1 = s1+2;
        double variartion_value2 = s1-2;
        if(s2 <= variartion_value1 && s2 >= variartion_value2)
        {
            flag=true;
        }
        return flag;
    }

}
