package io.github.thedeetch.openweathermap

/**
  * Corresponds to OpenWeatherMap current weather API response.
  * @param id the city ID
  * @param name the city name
  * @param main the main weather conditions
  * @param sys additional information regarding the location
  */
case class WeatherResponse(id: Long, name: String, main: Main, sys: Sys)

/**
  * Main weather conditions.
  * @param temp the current temperature
  */
case class Main(temp: Double)

/**
  * Information regarding the location.
  * @param country the country where the location is located
  */
case class Sys(country: String)
