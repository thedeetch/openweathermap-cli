package io.github.thedeetch.openweathermap

import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.JavaConversions._

/**
  * Tests for [[io.github.thedeetch.openweathermap.CurrentWeather]].
  */
class CurrentWeatherSuite extends FlatSpec with Matchers {

  val config = ConfigFactory.parseMap(Map("uri" -> "localhost", "key" -> ""))

  "The current weather" should "be returned by city name" in {
    val weatherByCity = new CurrentWeather(config).byCityName("london")

    weatherByCity should be(25.0)
  }

  it should "be returned when using the country code" in {
    val weatherByCity = new CurrentWeather(config).byCityName("London,GB")

    weatherByCity should be(25.0)
  }

  it should "be empty when the city name is empty" in {
    val weatherByCity = new CurrentWeather(config).byCityName("")

    weatherByCity should be(None)
  }
}
