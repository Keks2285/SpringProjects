package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Consumption;
import com.example.BarberShop.Models.SickLeave;
import org.springframework.data.repository.CrudRepository;

public interface SickLeaveRepository extends CrudRepository<SickLeave, Long> {
}
