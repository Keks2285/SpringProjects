package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Client;
import com.example.BarberShop.Models.Consumption;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository  extends CrudRepository<Client, Long> {
}
