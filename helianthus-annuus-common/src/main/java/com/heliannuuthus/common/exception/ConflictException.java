package com.heliannuuthus.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictException extends ResponseStatusException {
  public ConflictException() {
    super(HttpStatus.CONFLICT);
  }

  public ConflictException(String msg) {
    super(HttpStatus.CONFLICT, msg);
  }
}
