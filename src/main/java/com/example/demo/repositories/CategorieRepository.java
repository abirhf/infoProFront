package com.example.demo.repositories;

import com.example.demo.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Categorie findByNom(String nom);
}
