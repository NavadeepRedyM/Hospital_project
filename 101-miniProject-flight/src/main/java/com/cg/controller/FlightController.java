package com.cg.controller;

import java.util.List; // Imports List utility for collections
import java.util.Optional; // Imports Optional for handling potential null returns

import org.springframework.beans.factory.annotation.Autowired; // Imports Autowired annotation for dependency injection
import org.springframework.stereotype.Controller; // Marks this class as a Spring MVC controller
import org.springframework.ui.Model; // Imports Model to pass data from controller to view
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests
import org.springframework.web.bind.annotation.PathVariable; // Binds URI template variables to method parameters
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests
import org.springframework.web.bind.annotation.RequestMapping; // Maps web requests onto specific handler classes/methods
import org.springframework.web.bind.annotation.RequestParam; // Binds request parameters to method parameters

import com.cg.model.Flight; // Imports the Flight entity model
import com.cg.service.AirlinesService; // Imports AirlinesService for business logic
import com.cg.service.FlightService; // Imports FlightService for business logic
import com.cg.dto.FlightDTO; // Imports Data Transfer Object for Flight
import com.cg.exception.ResourceNotFound; // Imports custom exception class

@Controller // This class handles web requests and returns view names
@RequestMapping("/home") // Base mapping for all methods in this controller
public class FlightController {
	
	@Autowired // Injects the FlightService dependency
	FlightService service;
	
	@Autowired // Injects the AirlinesService dependency
	AirlinesService airlinesService;
	
	@GetMapping({"/", "/login"}) // Handles GET requests to /home or /home/login
    public String showInitialPage() {
	   return "login"; // Returns the name of the Thymeleaf template (login.html)
    }
	
	@GetMapping("/user-index") // Handles GET requests to /home/user-index (User dashboard)
    public String showUserDashboard(Model model) {
		 List<FlightDTO> allFlights = service.findAll(); // Retrieves all flights via the service layer
		 model.addAttribute("flights", allFlights); // Adds list of flights to the Model for the view
	     return "user-index"; // Returns the user-index view
    }
	
	@GetMapping("/admin-index") // Handles GET requests to /home/admin-index (Admin dashboard)
    public String showAdminDashboard(Model model) {
		 List<FlightDTO> allFlights = service.findAll(); // Retrieves all flights
		 model.addAttribute("flights", allFlights); // Adds flights to the Model
	     return "admin-index"; // Returns the admin-index view
    }
	 
	@GetMapping("/add") // Handles GET requests to show the add flight form
	public String showAddFlight(Model model) {
		 model.addAttribute("flight", new Flight()); // Adds an empty Flight object for form binding
		 model.addAttribute("airlinesList", airlinesService.findAll()); // Adds list of airlines for a dropdown
		 return "add-flight"; // Returns the add-flight view
	}

	@PostMapping("/create") // Handles POST requests to create a new flight
	public String createFlight(Flight flight) { // Binds form data to a Flight object
		service.create(flight); // Saves the flight via the service layer
		return "redirect:/home/admin-index"; // Redirects to the admin dashboard after success
	}

	@GetMapping("/delete/{fid}") // Handles GET requests to delete a flight by ID
	public String deleteFlight(@PathVariable("fid") int id) throws ResourceNotFound { // Extracts ID from URL path
		if (!service.findFlightById(id).isPresent()) { // Checks if the flight exists before deleting
			throw new ResourceNotFound("Flight ID " + id + " not found for deletion."); // Throws exception if not found
		}
		service.delete(id); // Deletes the flight via the service layer
		return "redirect:/home/admin-index"; // Redirects to the admin dashboard
	}
	
	@GetMapping("/edit/{fid}") // Handles GET requests to show the edit flight form by ID
	public String getFlightById(@PathVariable("fid") int id, Model model) throws ResourceNotFound {
		Flight flight = service.findFlightById(id) // Finds the flight by ID
                .orElseThrow(() -> new ResourceNotFound("Flight with ID " + id + " not found")); // Throws exception if not found
        
		model.addAttribute("flight", flight); // Adds the found flight object to the model
		model.addAttribute("airlinesList", airlinesService.findAll()); // Adds airlines list for dropdown
		return "edit-flight"; // Returns the edit-flight view
	}
	
	@PostMapping("/update") // Handles POST requests to update an existing flight
	public String updateFlight(Flight flight) { // Binds form data to a Flight object
		service.update(flight); // Updates the flight via the service layer
		return "redirect:/home/admin-index"; // Redirects to the admin dashboard
	}
	
	
	@GetMapping("/search") // Handles GET requests for flight search
	public String searchFlights(@RequestParam(value = "pickUp", required = false) String pickUp, // Binds 'pickUp' request param (optional)
	                            @RequestParam(value = "destination", required = false) String destination, // Binds 'destination' request param (optional)
	                            @RequestParam(value = "fclass", required = false) String fclass, // Binds 'fclass' request param (optional)
	                            Model model) {
	    List<FlightDTO> searchResults = service.searchFlights(pickUp, destination, fclass); // Calls service search method
	    
	    model.addAttribute("flights", searchResults); // Adds results to model
	    return "user-index"; // Returns the user-index view to display results
	}

	
	@GetMapping("/flights/book/{id}") // Handles GET requests to book a flight by ID
	public String bookFlight(@PathVariable("id") int id, Model model) throws ResourceNotFound {
	    Optional<Flight> flight = service.findFlightById(id); // Attempts to find the flight
	    
	    if (flight.isPresent()) { // Checks if flight was found
	        model.addAttribute("message", "Flight Booked Successfully!"); // Adds success message
	        model.addAttribute("flightId", id); // Adds ID detail
	        model.addAttribute("airline", flight.get().getEntreprise()); // Adds airline detail
	        
	        return "booking-success"; // Returns booking success view
	    } else {
	        throw new ResourceNotFound("Flight not found with ID: " + id); // Throws exception if not found
	    }
	}

}
