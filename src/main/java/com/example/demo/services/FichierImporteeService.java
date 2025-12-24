package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.FichierImportee;
import com.example.demo.repositories.FichierImporteeRepository;

@Service
public class FichierImporteeService {
    @Autowired
    private FichierImporteeRepository fichierImporteeRepository;

    public List<FichierImportee> getAllFichiers() {
        return fichierImporteeRepository.findAll();
    }

    public FichierImportee getFichierById(Long id) {
        return fichierImporteeRepository.findById(id).orElse(null);
    }

    public FichierImportee saveFichier(FichierImportee fichier) {
        return fichierImporteeRepository.save(fichier);
    }

    public void deleteFichier(Long id) {
        fichierImporteeRepository.deleteById(id);
    }
}

