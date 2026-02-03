package com.cg.controller;

import com.cg.model.Department;
import com.cg.model.Doctor;
import com.cg.model.User;
import com.cg.repository.DepartmentRepository;
import com.cg.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DoctorService doctorService;
    private final DepartmentRepository departmentRepository;

    public AdminController(DoctorService doctorService, DepartmentRepository departmentRepository) {
        this.doctorService = doctorService;
        this.departmentRepository = departmentRepository;
    }

    // Admin landing page
    @GetMapping("/index")
    public String adminIndex() {
        return "hospital/admin-index";
    }

    // ✅ Doctors list with optional department filter
    @GetMapping("/manage-doctors")
    public String showDoctorsList(@RequestParam(value = "departmentId", required = false) Long departmentId,
                                  Model model) {
        var doctors = (departmentId == null)
                ? doctorService.getAllDoctors()
                : doctorService.getDoctorsByDepartment(departmentId);

        model.addAttribute("listOfDoctors", doctors);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("selectedDepartmentId", departmentId);
        return "hospital/manage-doctors";
    }

    @GetMapping("/add-doctor")
    public String showAddForm(Model model) {
        Doctor doctor = new Doctor();
        doctor.setDepartment(new Department());
        doctor.setUser(new User());
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentRepository.findAll());
        return "hospital/add-doctor";
    }

    @PostMapping("/save-doctor")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/admin/manage-doctors";
    }

    @GetMapping("/edit-doctor/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.findDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentRepository.findAll());
        return "hospital/edit-doctor";
    }

    @PostMapping("/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/admin/manage-doctors";
    }
}
