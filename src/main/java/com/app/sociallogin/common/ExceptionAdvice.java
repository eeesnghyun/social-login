package com.app.sociallogin.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<MsgEntity> globalException(Exception e) {
		return ResponseEntity.badRequest()
				.body(new MsgEntity(e.getMessage(), ""));
	}


}
