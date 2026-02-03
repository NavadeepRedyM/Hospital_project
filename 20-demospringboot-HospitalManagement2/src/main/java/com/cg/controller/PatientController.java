package com.cg.controller;
 
import com.cg.model.Patient;
import com.cg.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
 
@Controller
@RequestMapping("/patient")
public class PatientController {
 
    private final PatientService patientService;
 
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
 
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        Patient patient = patientService.getPatientByUsername(principal.getName());
        model.addAttribute("patient", patient);
        return "patient/dashboard";
    }
 
    @GetMapping("/appointments")
    public String appointments(Model model, Principal principal) {
        Patient patient = patientService.getPatientByUsername(principal.getName());
        model.addAttribute("appointments",
                patientService.getAppointments(patient.getPatientId()));
        return "patient/appointments";
    }
 
    @GetMapping("/records")
    public String records(Model model, Principal principal) {
        Patient patient = patientService.getPatientByUsername(principal.getName());
        model.addAttribute("records",
                patientService.getMedicalRecords(patient.getPatientId()));
        return "patient/records";
    }
 
    @GetMapping("/bills")
    public String bills(Model model, Principal principal) {
        Patient patient = patientService.getPatientByUsername(principal.getName());
        model.addAttribute("bills",
                patientService.getBills(patient.getPatientId()));
        return "patient/bills";
    }
 
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Patient patient = patientService.getPatientByUsername(principal.getName());
        model.addAttribute("patient", patient);
        return "patient/profile";
    }
}
 