package com.example.demoModels.Repo;

import com.example.demoModels.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByFirstname(String firstname);
    Person findByLogin(String firstname);
}
