package com.restapi.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.restapi.person.dto.response.MessageResponseDTO;
import com.restapi.person.entity.Person;
import com.restapi.person.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository repo;
	
	@Autowired
	public PersonService (PersonRepository repo) {
		this.repo = repo;
	}
	
	public MessageResponseDTO savePerson(Person person) {
		Person saved = repo.save(person);
		return MessageResponseDTO.builder() //abre modo builder
				.message("saved person with ID: " + saved.getId()) // seta a msg
				.build(); //fecha modo builder
	}

}
