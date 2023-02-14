package com.heliannuuthus.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SerializedConfig {
    private static final String MEDIUM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern(MEDIUM_DATE_FORMAT).withZone(ZoneOffset.UTC);

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    // 基础序列化反序列化规则
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    // 对时间的序列化 统一使用时间为 Instant

    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(
        Instant.class,
        new JsonSerializer<>() {
          @Override
          public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers)
              throws IOException {
            gen.writeString(formatter.format(value));
          }
        });

    javaTimeModule.addDeserializer(
        Instant.class,
        new JsonDeserializer<>() {

          @Override
          public Instant deserialize(JsonParser p, DeserializationContext dctx) throws IOException {
            return Instant.from(formatter.parse(p.getText()));
          }
        });
    mapper.registerModule(javaTimeModule);
    return mapper;
  }
}
