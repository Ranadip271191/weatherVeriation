package com.WeatherReporting.API.stepDefinations;

import com.WeatherReporting.API.pages.landing_page;
import com.WeatherReporting.API.pages.weather_page;
import com.WeatherReporting.library;
import io.cucumber.java.en.*;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 *  This class will get the data from NTDV.com and update to results.properties and testData
 * @author Ranadip Bakshi
 */
public class GetWeatherDataFromNDTVui {
    WebDriver driver = null;
    WebDriverWait wait;
    ArrayList<String> data;
    landing_page lp;
    weather_page wp;
    public String cityname;
    String TCtimeStamp;
    @Given("User lunch browser")
    public void user_lunch_browser() throws IOException {
        driver = library.browser();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(library.GlobalValue("UIURL"));
        driver.manage().window().maximize();

    }

    @When("User navigate to weather page")
    public void user_navigate_to_weather_page() {
        lp = new landing_page(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",lp.NoThanks());
        lp.element_submenu().click();
        lp.element_submenuWeather().click();

    }
    @When("User send {string} under pin your City")
    public void user_send_under_pin_your_City(String city) {
        wp = new weather_page(driver);
        wp.cityname = city;
        cityname = city;
        if(wp.element_searchbox().getText() != null){
            wp.element_searchbox().clear();
        }
        wp.element_searchbox().sendKeys(city);
        if (!wp.element_cityName_from_pin().isSelected()){
            wp.element_cityName_from_pin().click();
        }
        wp.element_title().click();
    }

    @Then("User validate the weather data of the city from the map")
    public void user_validate_the_weather_data_of_the_city_from_the_map() throws ConfigurationException {
        TCtimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
        String tempIncelciusDegree = wp.element_tempInCeleciusDegree().getText();
        String tempInFahrenheit = wp.element_tempInFahrenheit().getText();
        String condition = wp.element_condition().getText();
        String humidity = wp.element_humidity().getText();
        String wind = wp.element_wind().getText();
        library.write_data_excel("UI_Data",cityname,tempIncelciusDegree,tempInFahrenheit,humidity,wind);

        StringBuffer sb_tempIncelciusDegree= new StringBuffer(tempIncelciusDegree);
        sb_tempIncelciusDegree.deleteCharAt(sb_tempIncelciusDegree.length()-1);
        int InttempInUIdegreeCelsius = Integer.parseInt(String.valueOf(sb_tempIncelciusDegree));
        String s2 = Integer.toString(InttempInUIdegreeCelsius);

        StringBuffer sb_tempInFahrenheit= new StringBuffer(tempInFahrenheit);
        sb_tempInFahrenheit.deleteCharAt(sb_tempInFahrenheit.length()-1);
        int InttempInFahrenheit = Integer.parseInt(String.valueOf(sb_tempInFahrenheit));
        String s3 = Integer.toString(InttempInFahrenheit);

        library.updatevalueto_Propertyfile(cityname,"UI","celcius",s2);
        library.updatevalueto_Propertyfile(cityname,"UI","fahrenheit",s3);

    }
    @Then("Close the browser")
    public void close_the_browser() {
        driver.close();
    }



}
