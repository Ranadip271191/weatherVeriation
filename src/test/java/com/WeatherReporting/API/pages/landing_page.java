package com.WeatherReporting.API.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class will contains all web element from ndtv.com landing page
 * @author Ranadip Bakshi
 */
public class landing_page {

    WebDriver driver;
    public landing_page(WebDriver driver){
        this.driver = driver;
    }
    public WebElement NoThanks(){
        By link_NoThanks = By.xpath("//*[text()='No Thanks']");
        return driver.findElement(link_NoThanks);
    }
    public WebElement element_submenu(){
        By submenu = By.xpath("//a[@id='h_sub_menu']");
        return driver.findElement(submenu);
    }
    public WebElement element_submenuWeather(){
        By submenuWeather = By.xpath("//a[text()='WEATHER']");
        return driver.findElement(submenuWeather);
    }


}
