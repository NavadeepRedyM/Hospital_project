package com.cg.service;

import java.util.List; // Imports List utility
import java.util.Optional; // Imports Optional utility
import java.util.stream.Collectors; // Imports Collectors for stream operations

import org.springframework.beans.factory.annotation.Autowired; // Imports Autowired annotation
import org.springframework.stereotype.Service; // Marks this class as a Service component

import com.cg.dto.FlightDTO; // Imports the DTO class
import com.cg.model.Flight; // Imports the Entity class
import com.cg.repository.FlightRepository; // Imports the repository interface

@Service // Indicates this class contains business logic and is a Spring service bean
public class FlightService {

    @Autowired // Injects the FlightRepository dependency
    FlightRepository repository;

    private FlightDTO convertToDTO(Flight flight) { // Helper method to map Entity to DTO
        return new FlightDTO( // Instantiates a new FlightDTO object using the Entity's data
            flight.getfId(),          
            flight.getEntreprise(),    
            flight.getPickUp(),        
            flight.getDestination(),   
            flight.getDepartureDate(), 
            flight.getFclass(),       
            flight.getPrice(),        
            flight.getAvailableSeats() 
        );
    }



    public List<FlightDTO> findAll() { // Public method to retrieve all flights as DTOs
        return repository.findAll() // Retrieves all Flight entities from the database
                .stream() // Converts the list to a stream for functional operations
                .map(this::convertToDTO) // Maps each entity in the stream to a DTO using the helper method
                .collect(Collectors.toList()); // Collects the DTOs back into a List
    }

    public Optional<Flight> findFlightById(int id) { // Finds a single flight entity by its ID
        return repository.findById(id); // Uses Spring Data JPA findById method
    }

    public Flight create(Flight flight) { // Creates a new flight entity
    	if (flight.getPickUp().equalsIgnoreCase(flight.getDestination())) { // Basic validation logic
            throw new IllegalArgumentException("Source and destination must be different."); // Throws an exception if validation fails
        }
        return repository.save(flight); // Saves the entity to the database via the repository
    }

    public void delete(int id) { // Deletes a flight by ID
        repository.deleteById(id); // Uses Spring Data JPA deleteById method
    }

    public Flight update(Flight flight) { // Updates an existing flight entity
        return repository.save(flight); // The save method handles both create and update (upsert)
    }

    public List<FlightDTO> searchFlights(String pickUp, String destination, String fclass) { // Method to search flights based on criteria
        List<Flight> flights;
        if ((pickUp == null || pickUp.isEmpty()) && // Checks if all search criteria are empty
            (destination == null || destination.isEmpty()) && 
            (fclass == null || fclass.isEmpty())) {
            flights = repository.findAll(); // Returns all flights if no criteria provided
        } else {
            flights = repository.findFlightsByCriteria(pickUp, destination, fclass); // Calls a custom repository method for specific search
        }
        
        return flights.stream() // Converts search results to a stream 
                      .map(this::convertToDTO) // Maps entities to DTOs
                      .collect(Collectors.toList()); // Collects DTOs into a List
    }
}
