package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Provider;
import com.example.BarberShop.Models.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByAddressContains(String address);
    Stock findByAddress(String address);
}
