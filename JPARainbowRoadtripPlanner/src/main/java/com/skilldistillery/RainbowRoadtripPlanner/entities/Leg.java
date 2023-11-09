package com.skilldistillery.RainbowRoadtripPlanner.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Leg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="estimated_miles")
	private Integer estimatedMiles;
	
	@Column(name="actual_miles")
	private Integer actualMiles;
	
	private String name;
	
	private String description;
	
	@Column(name="leg_number")
	private Integer legNumber;
	
	private String notes;
	
	@ManyToOne
	@JoinColumn(name = "start_destination_id")
	private Destination startDestination;
	
	@ManyToOne
	@JoinColumn(name = "end_destination_id")
	private Destination endDestination;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "trip_id")
	private Trip trip;
	
	private Boolean active;
	
	

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Leg() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getEstimatedMiles() {
		return estimatedMiles;
	}

	public void setEstimatedMiles(Integer estimatedMiles) {
		this.estimatedMiles = estimatedMiles;
	}

	public Integer getActualMiles() {
		return actualMiles;
	}

	public void setActualMiles(Integer actualMiles) {
		this.actualMiles = actualMiles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLegNumber() {
		return legNumber;
	}

	public void setLegNumber(Integer legNumber) {
		this.legNumber = legNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Destination getStartDestination() {
		return startDestination;
	}

	public void setStartDestination(Destination startDestination) {
		this.startDestination = startDestination;
	}

	public Destination getEndDestination() {
		return endDestination;
	}

	public void setEndDestination(Destination endDestination) {
		this.endDestination = endDestination;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leg other = (Leg) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Leg [id=").append(id).append(", estimatedMiles=").append(estimatedMiles)
				.append(", actualMiles=").append(actualMiles).append(", name=").append(name).append(", description=")
				.append(description).append(", legNumber=").append(legNumber).append(", notes=").append(notes)
				.append("]");
		return builder.toString();
	}
	
	

}
