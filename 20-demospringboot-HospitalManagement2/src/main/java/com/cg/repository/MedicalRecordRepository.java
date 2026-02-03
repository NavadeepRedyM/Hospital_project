package com.cg.repository;

import com.cg.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	List<MedicalRecord> findByPatientPatientId(Long patientId);
}