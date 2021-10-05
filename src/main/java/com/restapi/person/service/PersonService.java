package com.restapi.person.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.person.dto.request.PersonDTO;
import com.restapi.person.dto.response.MessageResponseDTO;
import com.restapi.person.entity.Person;
import com.restapi.person.exception.PersonNotFoundException;
import com.restapi.person.mapper.PersonMapper;
import com.restapi.person.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
	
	
	private PersonRepository repo;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	//substituído por @AllArgsConstructor
//	@Autowired
//	public PersonService (PersonRepository repo) {
//		this.repo = repo;
//	}
	
	public MessageResponseDTO savePerson(PersonDTO personDTO) {
		Person toSave = personMapper.toModel(personDTO);
		
		Person saved = repo.save(toSave);
		return createResponseMessage(saved.getId(), "Created person with id ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = repo.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findbyId(Long id) throws PersonNotFoundException {
		Person p = checkIfExists(id);
		return personMapper.toDTO(p);
	}

	public void delete(Long id) throws PersonNotFoundException {
		Person p = checkIfExists(id);
		repo.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, @Valid PersonDTO dto) throws PersonNotFoundException {
		Person p = checkIfExists(id);
		
		Person updated = repo.save(personMapper.toModel(dto));
		
		return createResponseMessage(updated.getId(), "Updated person with id ");
	}

	private MessageResponseDTO createResponseMessage(Long id, String msg) {
		return MessageResponseDTO
				.builder()
				.message(msg + id)
				.build();
	}
	
	private Person checkIfExists(Long id) throws PersonNotFoundException {
		return repo.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

}
