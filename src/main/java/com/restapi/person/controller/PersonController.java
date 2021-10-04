package com.restapi.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.person.dto.response.MessageResponseDTO;
import com.restapi.person.entity.Person;
import com.restapi.person.repository.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonRepository repo;
	//As vezes é mais vantajoso ter o autowired em um construtor que tenha o repo como argumento, isso facilitar na hora de criar mocks para testes
	
	@GetMapping
	public String getBook() {
		return "API Test";
	}
	
	@PostMapping
	public MessageResponseDTO savePerson(@RequestBody Person person) {
		Person saved = repo.save(person);
		return MessageResponseDTO.builder() //abre modo builder
				.message("saved person with ID: " + saved.getId()) // seta a msg
				.build(); //fecha modo builder
	}
	
}
