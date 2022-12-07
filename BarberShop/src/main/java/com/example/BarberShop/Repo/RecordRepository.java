package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
