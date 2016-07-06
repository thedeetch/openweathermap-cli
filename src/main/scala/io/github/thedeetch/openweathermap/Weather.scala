package io.github.thedeetch.openweathermap

/**
  * Created by nick on 7/5/16.
  */
case class WeatherResponse(coord: Coordinates, weather: Seq[Weather], base: String, main: Main, visibility: String,
                           wind: Wind, clouds: Clouds, dt: Long, sys: Sys, id: Long, name: String, cod: Int)

case class Coordinates(lat: Double, long: Double)

case class Weather(id: Int, main: String, description: String, icon: String)

case class Main(temp: Double, pressure: Double, humidity: Int, temp_min: Double, temp_max: Double)

case class Wind(speed: Double, deg: Int)

case class Clouds(all: String)

case class Sys(`type`: Int, id: Int, message: Double, country: String, sunrise: Long, sunset: Long)


