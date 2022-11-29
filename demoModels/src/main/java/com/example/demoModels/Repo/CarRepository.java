package com.example.demoModels.Repo;

import com.example.demoModels.Models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
    public interface CarRepository extends CrudRepository<Car, Long> {
}
