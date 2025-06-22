package com.anand.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Person;

public interface PersonRepo extends JpaRepository<Person,Integer>{

	public Person findByEmail(String email);
	
}
