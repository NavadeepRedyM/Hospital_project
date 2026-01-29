package com.cg.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Airlines")
public class Airlines {
	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;

	    @Column
	    private String name;

	    @OneToMany(mappedBy = "airlines",cascade = CascadeType.ALL) // Configures a one-to-many relationship with cascade operations
	    private List<Flight> flights; // A list holding all flights associated with this airline

	    @ElementCollection // Maps a collection of simple types (Strings) to a separate database table
	    @CollectionTable(name = "airline_classes", joinColumns = @JoinColumn(name = "airline_id")) // Defines the name and join column for the collection table
	    @Column(name = "class_name") // Names the column within the collection table that holds the class names
	    private List<String> classes; // A list holding the available flight class names (e.g., "Economy", "Business")

	    public Airlines() {
	    	
	    }

public Airlines(int id, String name, List<Flight> flights) {
	super();
	this.id = id;
	this.name = name;
	this.flights = flights;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Flight> getFlights() {
	return flights;
}

public void setFlights(List<Flight> flights) {
	this.flights = flights;
}
 
}
