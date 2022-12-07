package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Employer;
import com.example.BarberShop.Models.Vacation;
import org.springframework.data.repository.CrudRepository;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
}
