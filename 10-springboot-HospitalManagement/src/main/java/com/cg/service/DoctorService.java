package com.cg.service;

import com.cg.model.Doctor;
import com.cg.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor findDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }

    public List<Doctor> findByQualification(String qualification) {
        return doctorRepository.findByQualification(qualification);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> findDoctorsByQualification(String qualification) {
        return doctorRepository.findByQualification(qualification);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor getDoctorByUsername(String loggedInUserName) {
        return doctorRepository.findDoctorByUserName(loggedInUserName);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void deleteDoctorById(long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor addDoctor(Doctor doctor) {
        // If you want to encode nested user.password here, inject BCryptPasswordEncoder and do it.
        return doctorRepository.save(doctor);
    }

    // ✅ For Department filter
    public List<Doctor> getDoctorsByDepartment(Long deptId) {
        return doctorRepository.findByDepartmentId(deptId);
    }
}
