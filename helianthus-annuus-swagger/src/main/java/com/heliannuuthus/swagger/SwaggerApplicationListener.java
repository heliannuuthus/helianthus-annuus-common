package com.heliannuuthus.swagger;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

public class SwaggerApplicationListener implements ApplicationListener<WebServerInitializedEvent> {
  Logger logger = LoggerFactory.getLogger(SwaggerApplicationListener.class);

  @Override
  public void onApplicationEvent(WebServerInitializedEvent event) {
    try {
      logger.info(
          "http://{}:{}/swagger-ui.html",
          Inet4Address.getLocalHost().getHostAddress(),
          event.getWebServer().getPort());
    } catch (UnknownHostException e) {
      logger.error("initial error unknown host, swagger uri is empty");
    }
  }
}
