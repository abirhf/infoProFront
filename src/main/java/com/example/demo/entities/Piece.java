package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String nom;
    private String description;
    private String modeleVehicule;
    private String garantie;
    private Date dateLivraison;
    private int prix;
    private String image;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Piece parent;  
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Piece> sousPieces = new ArrayList<>();

    
    
    
    
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

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Piece getParent() {
		return parent;
	}

	public void setParent(Piece parent) {
		this.parent = parent;
	}

	public List<Piece> getSousPieces() {
		return sousPieces;
	}

	public void setSousPieces(List<Piece> sousPieces) {
		this.sousPieces = sousPieces;
	}

}

