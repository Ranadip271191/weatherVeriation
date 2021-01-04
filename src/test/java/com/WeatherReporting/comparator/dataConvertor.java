package com.WeatherReporting.comparator;
/**
 *  This class will kelvin data to celcius and farenheit
 * @author Ranadip Bakshi
 */

public class dataConvertor {

    public static float convertKelvinToCelsius(float kelvin) {

        return (float) (kelvin - 273.15);
    }

    public static float convertKelvinTofarenheit(float kelvin) {

        return (float) (((kelvin - 273.15) * 1.8) + 32);

    }

}
