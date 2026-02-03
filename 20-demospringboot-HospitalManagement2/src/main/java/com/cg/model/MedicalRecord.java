package com.cg.model;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String diagnosis;
	private String prescription;
	private String notes;
	private String recordDate;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
}
