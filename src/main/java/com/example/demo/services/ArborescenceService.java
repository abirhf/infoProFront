package com.example.demo.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ArborescenceDTO;
import com.example.demo.entities.Arborescence;
import com.example.demo.repositories.ArborescenceRepository;

@Service
public class ArborescenceService {

    @Autowired
    private ArborescenceRepository arborescenceRepository;

    public Arborescence getArborescenceById(Long id) {
        return arborescenceRepository.findById(id).orElse(null);
    }

    public Arborescence saveArborescence(Arborescence arb) {
        return arborescenceRepository.save(arb);
    }

    public void deleteArborescence(Long id) {
        arborescenceRepository.deleteById(id);
    }

    public List<ArborescenceDTO> getAllArborescences() {
        return arborescenceRepository.findAll().stream()
            .map(a -> {
                ArborescenceDTO dto = new ArborescenceDTO();
                dto.setId(a.getId());
                dto.setNom(a.getNom());

                if (a.getCategorie() != null) {
                    dto.setCategorieNom(a.getCategorie().getNom());
                }

                // Parent
                if (a.getParent() != null) {
                    dto.setParentId(a.getParent().getId());
                    dto.setParentNom(a.getParent().getNom());
                }

                return dto;
            })
            .collect(Collectors.toList());
    }
}
