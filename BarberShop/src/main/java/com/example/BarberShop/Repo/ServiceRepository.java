package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Service;
import com.example.BarberShop.Models.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {

    List<Service> findByNameserviceContains(String address);
    Service findByNameservice(String nameservice);
}
