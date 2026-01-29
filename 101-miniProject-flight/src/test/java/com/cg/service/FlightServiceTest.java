package com.cg.service;

import static org.mockito.Mockito.*; // Imports static methods for Mockito interaction (e.g., when, verify)
import static org.junit.jupiter.api.Assertions.*; // Imports static methods for JUnit assertions (e.g., assertEquals)

import com.cg.model.Flight; // Imports the Flight entity model
import com.cg.repository.FlightRepository; // Imports the repository interface being mocked
import org.junit.jupiter.api.Test; // Imports the @Test annotation
import org.junit.jupiter.api.extension.ExtendWith; // Imports the @ExtendWith annotation
import org.mockito.InjectMocks; // Imports the @InjectMocks annotation
import org.mockito.Mock; // Imports the @Mock annotation
import org.mockito.junit.jupiter.MockitoExtension; // Imports the Mockito JUnit 5 extension

import java.util.Optional; // Imports the Optional utility
import java.util.Date; // Imports the Date utility

@ExtendWith(MockitoExtension.class) // Integrates Mockito lifecycle with JUnit 5 test execution
public class FlightServiceTest {

    @Mock // Creates a mock instance of the repository dependency
    private FlightRepository flightRepository; // The dependency being mocked

    @InjectMocks // Injects the mock repository into the service instance
    private FlightService flightService; // The actual class under test (SUT)

    @Test // Marks this method as a test case to be run by JUnit
    public void testGetFlightById() {
        // 1. ARRANGE (Setup your data)
        Flight mockFlight = new Flight(); // Creates a simple mock data object
        mockFlight.setfId(500); // Sets properties of the mock object
        mockFlight.setEntreprise("Emirates");
        mockFlight.setDestination("Dubai");
        mockFlight.setPrice(1200.0);
        mockFlight.setDepartureDate(new Date());

        // Define behavior: When findById(500) is called, return our mockFlight wrapped in Optional
        when(flightRepository.findById(500)).thenReturn(Optional.of(mockFlight));

        // 2. ACT (Execute the method in your Service)
        Optional<Flight> result = flightService.findFlightById(500); // Calls the service method we are testing

        // 3. ASSERT (Verify results)
        assertNotNull(result); // Asserts that the result is not null (present)
        assertEquals("Emirates", result.get().getEntreprise()); // Asserts the returned airline name matches the mock data
        assertEquals("Dubai", result.get().getDestination()); // Asserts the returned destination matches the mock data
        
        // 4. VERIFY (Ensure the repo was actually contacted)
        verify(flightRepository, times(1)).findById(500); // Verifies that the repository's findById method was called exactly once with ID 500
    }
}
