package com.cg.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	private String role; // ROLE_ADMIN, ROLE_DOCTOR, ROLE_PATIENT

	// Getters & Setters
}
