package com.cg.model;
 
import jakarta.persistence.*;
 
@Entity
public class Billing {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private double amount;
    private String billDate;
    private String status; // PAID / UNPAID
 
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
 