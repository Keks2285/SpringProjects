package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Provider;
import com.example.BarberShop.Models.Supply;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

}
