package com.martin.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.api.persistence.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
public class RedisController {

  @Value("${api.key}")
  private String API_KEY;
  @Value("${url.api}")
  private String API_URL;

  @GetMapping("/{city}")
  @Cacheable(value = "weather_single", key ="#city")
  public Weather redisConnection(@PathVariable String city) {
    String apiUrl = API_URL + city + "/?key=" + API_KEY;
    RestTemplate restTemplate = new RestTemplate();
    String data = restTemplate.getForObject(apiUrl, String.class);
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(data);

      var days = rootNode.path("days");
      var today = days.get(0);
      var condition = rootNode.path("currentConditions");

      String address = rootNode.path("address").asText(null);
      String timezone = rootNode.path("timezone").asText(null);
      String dayDescription = rootNode.path("description").asText(null);
      String sunset = condition.path("sunset").asText(null);
      String sunrise = condition.path("sunrise").asText(null);
      String todayDate = today.path("datetime").asText(null);
      double temperature = fahrenheitToCelsius(today.path("temp").asDouble());

      return Weather.builder()
          .address(address)
          .timezone(timezone)
          .description(dayDescription)
          .sunset(sunset)
          .sunrise(sunrise)
          .dateTime(todayDate)
          .temp(temperature).build();

    }catch (Exception e){
      throw new RuntimeException("Failed to parse weather data", e);
    }
  }

  public static double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - 32) / 1.8;
  }
}
