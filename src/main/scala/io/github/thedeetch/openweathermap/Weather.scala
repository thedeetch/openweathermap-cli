package io.github.thedeetch.openweathermap

/**
  * Corresponds to OpenWeatherMap current weather API response.
  *
  * @param id      the city ID
  * @param name    the city name
  * @param main    the main weather conditions
  * @param message the error response message
  */
case class WeatherResponse(id: Option[Long], name: Option[String], main: Option[Main], message: Option[String])

/**
  * Main weather conditions.
  *
  * @param temp the current temperature
  */
case class Main(temp: Double)
