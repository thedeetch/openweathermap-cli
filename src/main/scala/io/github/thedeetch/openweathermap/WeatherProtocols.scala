package io.github.thedeetch.openweathermap

import spray.json.DefaultJsonProtocol

/**
  * JSON formats for OpenWeatherMap classes.
  */
trait WeatherProtocols extends DefaultJsonProtocol {
  implicit val mainFormat = jsonFormat1(Main.apply)
  implicit val sysFormat = jsonFormat1(Sys.apply)
  implicit val weatherResponseFormat = jsonFormat4(WeatherResponse.apply)
}
