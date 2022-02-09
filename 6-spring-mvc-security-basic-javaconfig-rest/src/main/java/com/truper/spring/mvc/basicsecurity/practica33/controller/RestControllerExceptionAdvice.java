package com.truper.spring.mvc.basicsecurity.practica33.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest req, Exception ex) {
		log.info("ex: {}", ex);
		return "lol";
	}
}
