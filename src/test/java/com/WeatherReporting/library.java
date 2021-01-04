package com.WeatherReporting;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
/**
 * Library :  Purpose of this class to reuse all the common funtions.
 *
 * This class will contains all logic related to API and UI
 *
 * @author Ranadip Bakshi
 */

public class library {

    public static RequestSpecification res;
    public static WebDriver driver;
    public static RequestSpecification requestspecification() throws IOException {
        /**
        This will set the Base URI and create session
         */
        if (res == null) {
            PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
            res = new RequestSpecBuilder().setBaseUri(GlobalValue("baseUrl"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();
            return res;
        }
        return res;
    }
    public static String GlobalValue(String key) throws IOException {
        /**
            To get value from property file
         */
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/com/WeatherReporting/global.properties");
        pro.load(fis);
        String property_value = pro.getProperty(key);
        return property_value;
    }

    public static WebDriver browser() throws IOException {
        /**
            To get browser value from property file and set it to WebDriver
         */
        String os = System.getProperty("os.name");

        String browser = GlobalValue("browser");
        if(browser.equals("chrome") && os.contains("Mac")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            driver = new ChromeDriver();
        }else if (browser.equals("firfox") && os.contains("Mac")){
            System.out.println("firfox driver");
        }else{
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver();//chromedriver
        }
         return driver;
    }

    public static void write_data_excel(String sheetname, String CityName, String tempInDegree, String tempInFahrenheit,String humidity, String windSpeed) throws ConfigurationException {
        /**
            To update data to excel sheet
         */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
        String excelFilePath = "src/test/java/com/WeatherReporting/testData.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheet(sheetname);

            Object[][] bookData = {
                    {CityName, tempInDegree, tempInFahrenheit, humidity, windSpeed, timeStamp},

            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;

                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);

                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }

            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void updatevalueto_Propertyfile(String propertyname,String method,String unit,String value) throws ConfigurationException {
        /**
            To update results in results.property file with a specific format
         */
        PropertiesConfiguration p;
        p = new PropertiesConfiguration("src/test/java/com/WeatherReporting/results.properties");
        p.setProperty(propertyname+"_"+method+"_"+unit,value);
        p.save();
    }
    public static String getValuefromproperty(String key,String filepath) throws IOException {
        /**
            To get value from results.properties file
         */
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream(filepath);
        pro.load(fis);
        String property_value = pro.getProperty(key);
        return property_value;
    }
}


