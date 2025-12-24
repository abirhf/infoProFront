package com.example.demo.dto;

import java.util.Date;

public class FichierImporteeDTO {
    private String reference;
    private String nom;
    private String categorieNom;
    private Long parentId;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCategorieNom() {
		return categorieNom;
	}
	public void setCategorieNom(String categorieNom) {
		this.categorieNom = categorieNom;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
    
    
}


