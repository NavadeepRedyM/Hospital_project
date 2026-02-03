package com.cg.controller;

import com.cg.model.Doctor;
import com.cg.model.MedicalRecord;
import com.cg.service.DoctorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) { this.doctorService = doctorService; }

    private Doctor getCurrentDoctor(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return doctorService.getDoctorByUsername(username); // ✅ use username mapping
    }

    // ✅ Dashboard (renders doctor-index.html safely)
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("doctor", getCurrentDoctor(userDetails));
        return "hospital/doctor-index";
    }

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("doctor", getCurrentDoctor(userDetails));
        return "hospital/doctor-profile";
    }

    @GetMapping("/today-appointments")
    public String todayAppointments(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("doctor", getCurrentDoctor(userDetails));
        return "hospital/doctor-appointments";
    }

    @GetMapping("/records")
    public String patientRecords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Doctor doctor = getCurrentDoctor(userDetails);
        model.addAttribute("doctor", doctor);
        model.addAttribute("records", doctor.getMedicalRecords());
        return "hospital/doctor-records";
    }

    @GetMapping("/add-diagnosis")
    public String diagnosisForm(Model model) {
        model.addAttribute("medicalRecord", new MedicalRecord());
        return "hospital/doctor-add-diagnosis";
    }
}