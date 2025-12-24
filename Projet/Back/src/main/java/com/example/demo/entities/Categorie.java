package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
	public class Categorie {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nom;

	    @ManyToOne
	    @JoinColumn(name = "arborescence_id")
	    private Arborescence arborescence;

	    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
	    private List<Piece> pieces = new ArrayList<>();

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

		

		//sans description

		public Arborescence getArborescence() {
			return arborescence;
		}

		public void setArborescence(Arborescence arborescence) {
			this.arborescence = arborescence;
		}

		public List<Piece> getPieces() {
			return pieces;
		}

		public void setPieces(List<Piece> pieces) {
			this.pieces = pieces;
		}
	}





