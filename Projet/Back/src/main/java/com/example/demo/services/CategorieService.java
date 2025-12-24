package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.entities.Categorie;
import com.example.demo.repositories.CategorieRepository;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<CategorieDTO> getAllCategorieDTOs() {
        List<Categorie> categories = categorieRepository.findAll();

        return categories.stream().map(c -> {
            CategorieDTO dto = new CategorieDTO();
            dto.setId(c.getId());
            dto.setNom(c.getNom());
            if (c.getArborescence() != null) {
                dto.setArborescenceId(c.getArborescence().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // Tu peux garder les autres méthodes si tu en as besoin :
    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie saveCategorie(Categorie categorie) {
        if (categorie.getId() != null && categorieRepository.existsById(categorie.getId())) {
            // Mise à jour
            return categorieRepository.save(categorie);
        } else {
            // Création
            return categorieRepository.save(categorie);
        }
    }


    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
    public CategorieDTO getCategorieDTOById(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie == null) return null;

        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
        if (categorie.getArborescence() != null) {
            dto.setArborescenceId(categorie.getArborescence().getId());
        }

        return dto;
    }

    
}
