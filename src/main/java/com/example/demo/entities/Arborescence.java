package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.*;
@Entity
public class Arborescence {



	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;

	    @ManyToOne
	    @JoinColumn(name = "categorie_id")
	    private Categorie categorie;

	    @ManyToOne
	    @JoinColumn(name = "parent_id")
	    private Arborescence parent;

	    // Getters & Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getNom() { return nom; }
	    public void setNom(String nom) { this.nom = nom; }

	    public Categorie getCategorie() { return categorie; }
	    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

	    public Arborescence getParent() { return parent; }
	    public void setParent(Arborescence parent) { this.parent = parent; }
	


}
