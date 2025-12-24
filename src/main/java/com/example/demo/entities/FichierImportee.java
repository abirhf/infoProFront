package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.*;

@Entity
	public class FichierImportee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nomFichier;
	    private Date dateImport;

	    @ManyToOne
	    @JoinColumn(name = "client_id")
	    private Client client;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNomFichier() {
			return nomFichier;
		}

		public void setNomFichier(String nomFichier) {
			this.nomFichier = nomFichier;
		}

		public Date getDateImport() {
			return dateImport;
		}

		public void setDateImport(Date dateImport) {
			this.dateImport = dateImport;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}
	}


