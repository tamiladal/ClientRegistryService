package com.bpp.clientregistryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class ClientRegistryExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientRegistryExceptionHandler.class);

	@ExceptionHandler(value = { InternalServerError.class })
	public ResponseEntity<String> handle500Exception() {
		LOGGER.error("An error occured, please try later");
		return new ResponseEntity<String>("An error occured, please try later",org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
