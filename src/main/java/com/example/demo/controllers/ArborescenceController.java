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

import com.example.demo.dto.ArborescenceDTO;
import com.example.demo.entities.Arborescence;
import com.example.demo.services.ArborescenceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/arborescences")
public class ArborescenceController {

    @Autowired
    private ArborescenceService arborescenceService;

    @GetMapping
    public ResponseEntity<List<ArborescenceDTO>> getAll() {
        List<ArborescenceDTO> dtos = arborescenceService.getAllArborescences();
        return ResponseEntity.ok(dtos);
    }



    @GetMapping("/{id}")
    public Arborescence getById(@PathVariable Long id) {
        return arborescenceService.getArborescenceById(id);
    }

    @PostMapping
    public Arborescence create(@RequestBody Arborescence arb) {
        return arborescenceService.saveArborescence(arb);
    }

    @PutMapping("/{id}")
    public Arborescence update(@PathVariable Long id, @RequestBody Arborescence arb) {
        arb.setId(id);
        return arborescenceService.saveArborescence(arb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        arborescenceService.deleteArborescence(id);
    }
}

