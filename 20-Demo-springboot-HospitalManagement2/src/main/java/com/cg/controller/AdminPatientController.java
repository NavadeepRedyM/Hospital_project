package com.cg.controller;

import com.cg.model.Patient;
import com.cg.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/patients")
public class AdminPatientController {

    private final PatientService service;

    public AdminPatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("patients", (q == null || q.isBlank()) ? service.findAll() : service.search(q));
        model.addAttribute("q", q);
        return "hospital/manage-patients";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "hospital/add-patient";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", service.findById(id));
        return "hospital/edit-patient";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("patient") Patient patient,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            // Re-display the same form with error messages
            return (patient.getId() == null) ? "hospital/add-patient" : "hospital/edit-patient";
        }

        service.save(patient);
        return "redirect:/admin/patients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/patients";
    }
}