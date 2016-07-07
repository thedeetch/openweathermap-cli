# openweathermap-cli

[![Build Status](https://travis-ci.org/thedeetch/openweathermap-cli.svg?branch=master)](https://travis-ci.org/thedeetch/openweathermap-cli)

Simple API for getting current conditions from OpenWeatherMap

## API Keys

An [OpenWeatherMap API key](http://openweathermap.org/appid) needs to be provided in several ways:

1. Set the `OPENWEATHERMAP_APPID` environment variable to the value of the key.
2. Create an `application.conf` and put it on the classpath.  An example configuration file:
    ```
    OpenWeatherMap {
      uri = "http://api.openweathermap.org/data/2.5/weather"
      appid = ${OPENWEATHERMAP_APPID}
    }
    ```
3. Set the Java system property `OpenWeatherMap.appid`.

## Configuring

Any OpenWeatherMap compatible API can be substituted, but will default to `api.openweathermap.org`.  Set `OpenWeatherMap.uri` in either the Java system properties, or `application.conf` to the desired URL to the service.

## Running

From a jar:

```
$ export OPENWEATHERMAP_APPID=myappid
$ sbt assembly
$ java -jar target/scala-2.11/openweathermap-cli-assembly-1.0.jar io.github.thedeetch.openweathermap.CurrentWeather
```

From sbt:

```
$ export OPENWEATHERMAP_APPID=myappid
$ sbt run
```

## Building and Running Tests

```
$ sbt compile
```
