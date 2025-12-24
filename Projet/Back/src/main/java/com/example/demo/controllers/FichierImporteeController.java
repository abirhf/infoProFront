package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.entities.FichierImportee;
import com.example.demo.services.FichierImporteeService;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/fichiers")
public class FichierImporteeController {

    @Autowired
    private FichierImporteeService fichierService;

    @GetMapping
    public List<FichierImportee> getAll() {
        return fichierService.getAllFichiers();
    }

    @GetMapping("/{id}")
    public FichierImportee getById(@PathVariable Long id) {
        return fichierService.getFichierById(id);
    }

    @PostMapping
    public FichierImportee create(@RequestBody FichierImportee fichier) {
        return fichierService.saveFichier(fichier);
    }

    @PutMapping("/{id}")
    public FichierImportee update(@PathVariable Long id, @RequestBody FichierImportee fichier) {
        fichier.setId(id);
        return fichierService.saveFichier(fichier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fichierService.deleteFichier(id);
    }
}

