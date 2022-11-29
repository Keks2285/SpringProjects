package com.example.demoModels.Repo;

import com.example.demoModels.Models.Computer;
import com.example.demoModels.Models.Person;
import org.springframework.data.repository.CrudRepository;

public interface ComputerRepository extends CrudRepository<Computer, Long> {
    Computer findByModel(String model);
}
