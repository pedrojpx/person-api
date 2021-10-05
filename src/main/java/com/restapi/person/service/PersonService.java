package com.restapi.person.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.person.dto.request.PersonDTO;
import com.restapi.person.dto.response.MessageResponseDTO;
import com.restapi.person.entity.Person;
import com.restapi.person.mapper.PersonMapper;
import com.restapi.person.repository.PersonRepository;

@Service
public class PersonService {
	
	
	private PersonRepository repo;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService (PersonRepository repo) {
		this.repo = repo;
	}
	
	public MessageResponseDTO savePerson(PersonDTO personDTO) {
		Person toSave = personMapper.toModel(personDTO);
		
		Person saved = repo.save(toSave);
		return MessageResponseDTO
				.builder() //abre modo builder
				.message("saved person with ID: " + saved.getId()) // seta a msg
				.build(); //fecha modo builder
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = repo.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

}
