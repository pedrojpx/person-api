package com.restapi.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
	
	public PersonNotFoundException (Long id) {
		super("Não existe pessoa com id " + id);
	}
}
