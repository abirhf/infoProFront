package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PieceDTO {

	
	


	    private Long id;
	    private String reference;
	    private String nom;
	    private String description;
	    private String modeleVehicule;
	    private String garantie;
	    private Date dateLivraison;
	    private long prix;
	    private String image;

	    private Long categorieId;
	    private String categorieNom;

	    private Long parentId;
	    private String parentNom;

	    private List<PieceDTO> enfants = new ArrayList<>();

	    // 🔹 Getters & Setters

	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }

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

	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getModeleVehicule() {
	        return modeleVehicule;
	    }
	    public void setModeleVehicule(String modeleVehicule) {
	        this.modeleVehicule = modeleVehicule;
	    }

	    public String getGarantie() {
	        return garantie;
	    }
	    public void setGarantie(String garantie) {
	        this.garantie = garantie;
	    }

	    public Date getDateLivraison() {
	        return dateLivraison;
	    }
	    public void setDateLivraison(Date dateLivraison) {
	        this.dateLivraison = dateLivraison;
	    }

	    public long getPrix() {
	        return prix;
	    }
	    public void setPrix(long prix) {
	        this.prix = prix;
	    }

	    public String getImage() {
	        return image;
	    }
	    public void setImage(String image) {
	        this.image = image;
	    }

	    public Long getCategorieId() {
	        return categorieId;
	    }
	    public void setCategorieId(Long categorieId) {
	        this.categorieId = categorieId;
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

	    public String getParentNom() {
	        return parentNom;
	    }
	    public void setParentNom(String parentNom) {
	        this.parentNom = parentNom;
	    }

	    public List<PieceDTO> getEnfants() {
	        return enfants;
	    }
	    public void setEnfants(List<PieceDTO> enfants) {
	        this.enfants = enfants;
	    }
	

    
    
    


}

