package com.heliannuuthus.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
  @Bean
  public WebClient lbWebClient(ObjectMapper objectMapper) {
    return WebClient.builder()
        .codecs(
            configurer -> {
              configurer
                  .defaultCodecs()
                  .jackson2JsonEncoder(
                      new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
              configurer
                  .defaultCodecs()
                  .jackson2JsonDecoder(
                      new Jackson2CborDecoder(objectMapper, MediaType.APPLICATION_JSON));
            })
        .build();
  }
}
