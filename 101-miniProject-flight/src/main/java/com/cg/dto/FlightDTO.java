package com.cg.dto;

import java.util.Date; // Imports the Date utility class
public class FlightDTO { // Data Transfer Object (DTO) class for flight data presentation
    
    private int fId; // Unique identifier for the flight
    private String airlineName; // Name of the operating airline
    private String pickUp; // Departure location
    private String destination; // Arrival location
    private Date departureDate; // Date and time of departure
    private String flightClass; // e.g., Economy, Business, First
    private double price; // Price of the ticket
    private long availableSeats; // Number of seats currently available

    public FlightDTO() {
    } // Default constructor

    public FlightDTO(int fId, String airlineName, String pickUp, String destination, 
                     Date departureDate, String flightClass, double price, long availableSeats) {
        this.fId = fId; // Constructor with all fields
        this.airlineName = airlineName;
        this.pickUp = pickUp;
        this.destination = destination;
        this.departureDate = departureDate;
        this.flightClass = flightClass;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Getters and Setters (Accessor and Mutator methods)
    public int getfId() { 
    	return fId; // Getter for flight ID
    	}
    public void setfId(int fId) { 
    	this.fId = fId; // Setter for flight ID
    	}

    public String getAirlineName() {
    	return airlineName; // Getter for airline name
    	}
    public void setAirlineName(String airlineName) { 
    	this.airlineName = airlineName; // Setter for airline name
    	}

    public String getPickUp() {
    	return pickUp; // Getter for pickup location
    	}
    public void setPickUp(String pickUp) {
    	this.pickUp = pickUp; // Setter for pickup location
    	}

    public String getDestination() { 
    	return destination; // Getter for destination location
    	}
    public void setDestination(String destination) {
    	this.destination = destination; // Setter for destination location
    	}

    public Date getDepartureDate() {
    	return departureDate; // Getter for departure date
    	}
    public void setDepartureDate(Date departureDate) {
    	this.departureDate = departureDate; // Setter for departure date
    	}

    public String getFlightClass() {
    	return flightClass; // Getter for flight class
    	}
    public void setFlightClass(String flightClass) {
    	this.flightClass = flightClass; // Setter for flight class
    	}

    public double getPrice() {
    	return price; // Getter for price
    	}
    public void setPrice(double price) { 
    	this.price = price; // Setter for price
    	}

    public long getAvailableSeats() {
    	return availableSeats; // Getter for available seats
    	}
    public void setAvailableSeats(long availableSeats) { 
    	this.availableSeats = availableSeats; // Setter for available seats
    	}
}
