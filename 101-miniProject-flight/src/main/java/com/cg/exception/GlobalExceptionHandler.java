package com.cg.exception;

import org.springframework.ui.Model; // Imports Model to pass data to the error view
import org.springframework.web.bind.annotation.ControllerAdvice; // Marks this class as a global exception handler
import org.springframework.web.bind.annotation.ExceptionHandler; // Specifies which exceptions a method should handle
import org.springframework.dao.DataIntegrityViolationException; // Imports exception for database constraint violations
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException; // Imports exception for invalid input types
import java.util.Date; // Imports Date utility (though unused in the provided code)

@ControllerAdvice // Annotation to handle exceptions across the entire application's controllers
public class GlobalExceptionHandler {

    // 1. Handle Resource Not Found (e.g., Flight ID not found)
    @ExceptionHandler(ResourceNotFound.class) // Catches the specific custom ResourceNotFound exception
    public String handleResourceNotFound(ResourceNotFound ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage()); // Adds the exception message to the view model
        model.addAttribute("errorCode", "404 - Not Found"); // Adds a specific error code
        return "error"; // Returns the 'error' view name (e.g., error.html/jsp)
    }

    // 2. Handle Data Integrity Violations (e.g., trying to delete a flight with active bookings)
    @ExceptionHandler(DataIntegrityViolationException.class) // Catches database integrity violations
    public String handleDataIntegrity(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorMessage", "Cannot perform this action due to database constraints (e.g., related records exist)."); // Provides user-friendly error message
        return "error"; // Returns the 'error' view
    }

    // 3. Handle Method Argument Mismatches (e.g., passing a string into an ID field)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class) // Catches issues when URL path variables/request params don't match method arg types
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("errorMessage", "Invalid input format provided in the URL."); // Provides user-friendly error message
        return "error"; // Returns the 'error' view
    }

    // 4. Fallback for all other Exceptions
    @ExceptionHandler(Exception.class) // Catches any remaining, unhandled exceptions as a general fallback
    public String handleGlobalException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage()); // Generic error message with details
        return "error"; // Returns the 'error' view
    }
}
