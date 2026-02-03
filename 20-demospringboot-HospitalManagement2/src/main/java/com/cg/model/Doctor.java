package com.cg.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;

	private String specialization;
	private String phone;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "doctor")
	private List<MedicalRecord> medicalRecords;
}
