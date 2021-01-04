package com.WeatherReporting.API.stepDefinations;

import com.WeatherReporting.API.resources.APIResources;
import com.WeatherReporting.comparator.dataConvertor;
import com.WeatherReporting.library;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;

/**
 *  This class will get the data from API and update to results.properties and testData
 * @author Ranadip Bakshi
 */


public class GetWeatherDataFromAPI {

    RequestSpecification req;
    static String cityname;
    public Response response;
    String TCtimeStamp;


    @Given("User send {string}")
    public void user_send_cityname(String city) throws IOException {
        RestAssured.baseURI ="api.openweathermap.org";
        cityname = city;
        req = given().spec(library.requestspecification());
    }

    @When("user calls {string} api with {string} http request")
    public void user_calls_api_with_http_request(String apiName, String method) {
        APIResources apiResources = APIResources.valueOf(apiName);
        response = req.queryParam("q",cityname).queryParam("appid","7fe67bf08c80ded756e598d6f8fedaea")
                .when().get(apiResources.getResources());

    }

    @Then("API call got success with status code {string}")
    public void api_call_got_success_with_status_code(String string) throws ConfigurationException {
        TCtimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new Date());
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        float TempInKelvin =js.getFloat("main.temp");
        float Fhumidity = js.getFloat("main.humidity");
        float FwindSpeed = js.getFloat("wind.speed");
        String CityName = js.get("name");

        String humidity = Float.toString(Fhumidity);
        String windSpeed = Float.toString(FwindSpeed);

        String tempDegreeCelsius = String.valueOf(dataConvertor.convertKelvinToCelsius(TempInKelvin));
        String tempfarenheit= String.valueOf(dataConvertor.convertKelvinTofarenheit(TempInKelvin));
        library.write_data_excel("API_Data",CityName,tempDegreeCelsius,tempfarenheit,humidity,windSpeed);

        library.updatevalueto_Propertyfile(cityname,"API","celcius",tempDegreeCelsius);
        library.updatevalueto_Propertyfile(cityname,"API","fahrenheit",tempfarenheit);


    }

}
