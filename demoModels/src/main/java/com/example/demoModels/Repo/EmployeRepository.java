package com.example.demoModels.Repo;

import com.example.demoModels.Models.Employe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeRepository extends CrudRepository<Employe, Long>{

    List<Employe> findByFirstnameContains(String firstname);
    List<Employe> findByFirstname(String firstname);
}
