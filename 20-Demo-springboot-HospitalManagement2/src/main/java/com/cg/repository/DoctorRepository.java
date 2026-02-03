package com.cg.repository;

import com.cg.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByName(String name);
    List<Doctor> findByQualification(String qualification);

    @Query("SELECT d FROM Doctor d WHERE d.user.username = :uname")
    Doctor findDoctorByUserName(@Param("uname") String username);

    // For department filter
    List<Doctor> findByDepartmentId(Long departmentId);
}