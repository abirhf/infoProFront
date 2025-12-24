package com.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Sous-traitant")
public class SousTraitant extends Client {
    private String domaineSpecialisation;

	public String getDomaineSpecialisation() {
		return domaineSpecialisation;
	}

	public void setDomaineSpecialisation(String domaineSpecialisation) {
		this.domaineSpecialisation = domaineSpecialisation;
	}
}
