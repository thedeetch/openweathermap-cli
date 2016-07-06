package io.github.thedeetch.openweathermap

import akka.http.scaladsl.model.Uri
import com.typesafe.config.Config

/**
  * Obtains the current weather.
  *
  * @param uri the base url for OpenWeatherMap
  * @see [[http://openweathermap.org/current#name]]
  */
class CurrentWeather(uri: Uri, appId: String) extends WeatherProtocols {

  def this(config: Config) = {
    this(config.getString("uri"), config.getString("appid"))
  }

  /**
    * Returns the current weather by city name.
    *
    * @param city the city name and optional country code, separated by comma
    * @return a [[io.github.thedeetch.openweathermap.Weather]] object for that location
    */
  def byCityName(city: String): WeatherResponse = {
    ???
  }

  /**
    * Returns the current weather for a city by its OpenWeatherMap city ID.
    *
    * @param id the city ID
    * @return a [[io.github.thedeetch.openweathermap.Weather]] object for that location
    */
  def byCityId(id: Long): WeatherResponse = {
    ???
  }

  /**
    * Returns the current weather by geographic coordinates.
    *
    * @param latitude  the latitude in degrees
    * @param longitude the longitude in degrees
    * @return a [[io.github.thedeetch.openweathermap.Weather]] object for that location
    */
  def byCoordinates(latitude: Double, longitude: Double): WeatherResponse = {
    ???
  }

  /**
    * Returns the current weather by US ZIP code.
    *
    * @param zip a US ZIP code
    * @return a [[io.github.thedeetch.openweathermap.Weather]] object for that location
    */
  def byZipCode(zip: String): WeatherResponse = {
    ???
  }
}
