package com.example.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Distributeur")
public class Distributeur extends Client {
    private String zoneDistribution;

	public String getZoneDistribution() {
		return zoneDistribution;
	}

	public void setZoneDistribution(String zoneDistribution) {
		this.zoneDistribution = zoneDistribution;
	}
}
