package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.entities.Categorie;
import com.example.demo.services.CategorieService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    // ✅ Endpoint manquant : GET /api/categories
    @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAll() {
        List<CategorieDTO> categories = categorieService.getAllCategorieDTOs();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDTO> getById(@PathVariable Long id) {
        CategorieDTO dto = categorieService.getCategorieDTOById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Categorie create(@RequestBody Categorie cat) {
        return categorieService.saveCategorie(cat);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Long id, @RequestBody Categorie cat) {
        cat.setId(id);
        return categorieService.saveCategorie(cat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
