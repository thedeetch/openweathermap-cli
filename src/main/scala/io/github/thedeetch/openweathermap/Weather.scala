package io.github.thedeetch.openweathermap

/**
  * Created by nick on 7/5/16.
  */
case class WeatherResponse(id: Long, name: String, main: Main, sys: Sys)

case class Main(temp: Double)

case class Sys(country: String)


