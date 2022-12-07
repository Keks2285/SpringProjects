package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialRepository extends CrudRepository<Material, Long> {
    List<Material> findByNameContains(String name);
    Material findByName(String name);
}
