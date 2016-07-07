package io.github.thedeetch.openweathermap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future

/**
  * Tests for [[io.github.thedeetch.openweathermap.CurrentWeather]].
  */
class CurrentWeatherSuite extends FlatSpec with Matchers with ScalaFutures {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  /**
    * Builds a simple HTTP server and responds with the given string at the root.
    *
    * @param respondWith the string to respond with
    * @return the URI where the server is bound
    */
  def buildServer(respondWith: String): Future[Uri] = {
    val route =
      pathSingleSlash {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, respondWith))
        }
      }

    Http().bindAndHandle(route, "localhost", 0)
      .map(binding => s"http://localhost:${binding.localAddress.getPort}")
  }

  "The current weather" should "be returned by city name" in {
    val response =
      """
        |{
        |  "main": {
        |    "temp": 293.28
        |  },
        |  "id": 2643743,
        |  "name": "London",
        |  "cod": 200
        |}
      """.stripMargin
    val result = buildServer(response)
      .flatMap {
        binding => new CurrentWeather(binding, "").byCityName("london")
      }

    result.futureValue should be(WeatherResponse(Some(2643743), Some("London"), Some(Main(293.28)), None))
  }

  it should "be returned when using the country code" in {
    val response =
      """
        |{
        |  "main": {
        |    "temp": 293.28
        |  },
        |  "id": 2643743,
        |  "name": "London",
        |  "cod": 200
        |}
      """.stripMargin
    val result = buildServer(response)
      .flatMap {
        binding => new CurrentWeather(binding, "").byCityName("london,gb")
      }

    result.futureValue should be(WeatherResponse(Some(2643743), Some("London"), Some(Main(293.28)), None))
  }

  it should "return a not found message when the city name is empty" in {
    val response =
      """
        |{
        |  "cod": "404",
        |  "message": "Error: Not found city"
        |}
      """.stripMargin
    val result = buildServer(response)
      .flatMap {
        binding => new CurrentWeather(binding, "").byCityName("")
      }

    result.futureValue should be(WeatherResponse(None, None, None, Some("Error: Not found city")))
  }
}
