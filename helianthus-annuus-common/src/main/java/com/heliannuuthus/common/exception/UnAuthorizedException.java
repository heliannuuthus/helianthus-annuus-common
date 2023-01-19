package com.heliannuuthus.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnAuthorizedException extends ResponseStatusException {
    public UnAuthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException(String msg) {
        super(HttpStatus.UNAUTHORIZED, msg);
    }
}
