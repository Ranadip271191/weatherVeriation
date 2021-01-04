Feature: Validate Weather Report
  @GetWeatherFromAPI
  Scenario Outline: Get weather data from API
    Given User send "<cityname>"
    When user calls "GetWeatherAPI" api with "GET" http request
    Then API call got success with status code "200"
    Examples:
      | cityname |
      | Jaipur |
      | Kolkata |

  @GetWeatherFromNDTV
  Scenario Outline: Get weather Report NDTV UI
    Given User lunch browser
    When User navigate to weather page
    And User send "<cityname>" under pin your City
    Then User validate the weather data of the city from the map
    And Close the browser
    Examples:
      | cityname |
      | Jaipur |
      | Kolkata |

  @CompareVeriation
  Scenario Outline: Compare weather data from API and UI
    Given User get "<cityname>"
    When User get UI and API data
    Then User compare the veriation
    Examples:
      | cityname |
      | Jaipur |
      | Kolkata |









