package com.WeatherReporting.API.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class will return all the web element from ndtv.com Weather page
 * @author Ranadip Bakshi
 */


public class weather_page {
    WebDriver driver;
    public String cityname = null;

    public weather_page(WebDriver driver){
        this.driver = driver;
    }

    public WebElement element_searchbox(){
        By searchbox = By.xpath("//input[@class='searchBox']");
        return driver.findElement(searchbox);
    }

    public WebElement element_cityName_from_pin(){
        By cityName_from_pin = By.xpath("//input[@id='" + cityname + "']");
        return driver.findElement(cityName_from_pin);
    }
    public WebElement element_title(){
        By citytitle = By.xpath(" //div[@title='"+cityname+"']");
        return driver.findElement(citytitle);
    }
    public WebElement element_tempInCeleciusDegree(){
        By tempInCeleciusDegree = By.xpath("//div[@title='"+cityname+"']/div/span[1]");
        return driver.findElement(tempInCeleciusDegree);
    }
    public WebElement element_tempInFahrenheit()
    {
        By tempInFahrenheit = By.xpath("//div[@title='"+cityname+"']/div/span[2]");
        return driver.findElement(tempInFahrenheit);
    }
    public WebElement element_condition(){
        By condition = By.xpath("//div[@class='leaflet-popup-content']/div/span[1]");
        return driver.findElement(condition);
    }
    public WebElement element_wind(){
        By wind = By.xpath("//div[@class='leaflet-popup-content']/div/span[2]");
        return driver.findElement(wind);
    }
    public WebElement element_humidity(){
        By humidity = By.xpath("//div[@class='leaflet-popup-content']/div/span[3]");
        return driver.findElement(humidity);
    }
}
