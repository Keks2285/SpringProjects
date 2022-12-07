package com.example.BarberShop.Repo;

import com.example.BarberShop.Models.Material;
import com.example.BarberShop.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
