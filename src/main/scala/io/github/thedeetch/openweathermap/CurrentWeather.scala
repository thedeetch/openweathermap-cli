package io.github.thedeetch.openweathermap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.typesafe.config.Config

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._

/**
  * Obtains the current weather.
  *
  * @param uri the base url for OpenWeatherMap
  * @see [[http://openweathermap.org/current#name]]
  */
class CurrentWeather(uri: Uri, appId: String) extends WeatherProtocols {

  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  def this(config: Config) = {
    this(config.getString("uri"), config.getString("appid"))
  }

  /**
    * Returns the current weather by city name.
    *
    * @param city the city name and optional country code, separated by comma
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCityName(city: String): Future[WeatherResponse] = {
    val requestUri = uri.withQuery(Uri.Query("q" -> city, "appid" -> appId))
    val response = Http().singleRequest(HttpRequest(uri = requestUri))
      .flatMap {
        case HttpResponse(status, headers, entity, _) =>
          Unmarshal(entity).to[WeatherResponse]
      }
  }

  /**
    * Returns the current weather for a city by its OpenWeatherMap city ID.
    *
    * @param id the city ID
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCityId(id: Long): WeatherResponse = {
    ???
  }

  /**
    * Returns the current weather by geographic coordinates.
    *
    * @param latitude  the latitude in degrees
    * @param longitude the longitude in degrees
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCoordinates(latitude: Double, longitude: Double): WeatherResponse = {
    ???
  }

  /**
    * Returns the current weather by US ZIP code.
    *
    * @param zip a US ZIP code
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byZipCode(zip: String): WeatherResponse = {
    ???
  }
}
