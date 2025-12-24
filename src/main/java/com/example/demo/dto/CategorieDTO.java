package com.example.demo.dto;

public class CategorieDTO {
    private Long id;
    private String nom;
    private String description;
    private Long arborescenceId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getArborescenceId() {
		return arborescenceId;
	}
	public void setArborescenceId(Long arborescenceId) {
		this.arborescenceId = arborescenceId;
	}
}

