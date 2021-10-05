package com.restapi.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.person.dto.request.PersonDTO;
import com.restapi.person.dto.response.MessageResponseDTO;
import com.restapi.person.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonService service;
	//As vezes é mais vantajoso ter o autowired em um construtor que tenha o repo como argumento, isso facilitar na hora de criar mocks para testes
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //o defaul é 200, mas como criou um item, o certo é 201
	public MessageResponseDTO savePerson(@RequestBody @Valid PersonDTO person) { //@valid manda o spring validar o PersonDTO
		return service.savePerson(person);
	}
	
	@GetMapping
	public List<PersonDTO> listAll() {
		return service.listAll();
	}
	
}
