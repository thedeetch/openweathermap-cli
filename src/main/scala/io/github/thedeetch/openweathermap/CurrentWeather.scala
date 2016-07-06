package io.github.thedeetch.openweathermap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.typesafe.config.Config

import scala.concurrent.Future

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
    this(config.getString("OpenWeatherMap.uri"), config.getString("OpenWeatherMap.appid"))
  }

  /**
    * Returns the current weather by city name.
    *
    * @param city the city name and optional country code, separated by comma
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCityName(city: String): Future[WeatherResponse] = {
    this.byUri(uri.withQuery(Uri.Query("q" -> city)))
  }

  /**
    * Returns the current weather for a city by its OpenWeatherMap city ID.
    *
    * @param id the city ID
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCityId(id: Long): Future[WeatherResponse] = {
    this.byUri(uri.withQuery(Uri.Query("id" -> id.toString)))
  }

  /**
    * Returns the current weather by geographic coordinates.
    *
    * @param latitude  the latitude in degrees
    * @param longitude the longitude in degrees
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byCoordinates(latitude: Double, longitude: Double): Future[WeatherResponse] = {
    this.byUri(uri.withQuery(Uri.Query("lat" -> latitude.toString, "lon" -> longitude.toString)))
  }

  /**
    * Returns the current weather by US ZIP code.
    *
    * @param zip a US ZIP code
    * @return a [[io.github.thedeetch.openweathermap.WeatherResponse]] object for that location
    */
  def byZipCode(zip: String): Future[WeatherResponse] = {
    this.byUri(uri.withQuery(Uri.Query("zip" -> zip)))
  }

  /**
    * Returns the current weather with a given request URI. The OpenWeatherMap API ID will be appended to the given URI.
    *
    * @param uri the URI to request
    * @return the WeatherResponse returned by the request
    */
  private def byUri(uri: Uri): Future[WeatherResponse] = {
    val requestUri = uri.withQuery(("appid", appId) +: uri.query())
    Http().singleRequest(HttpRequest(uri = requestUri))
      .flatMap {
        case HttpResponse(StatusCodes.OK, headers, entity, _) =>
          Unmarshal(entity).to[WeatherResponse]
      }
  }
}
