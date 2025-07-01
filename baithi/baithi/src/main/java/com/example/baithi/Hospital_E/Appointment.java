package com.example.baithi.Hospital_E;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FutureOrPresent(message = "Appointment date must be today or later")
    private LocalDate appointmentDate;

    @NotBlank
    private String status;

    @ManyToOne @JoinColumn(name = "patient_id") @NotNull
    private Patient patient;

    @ManyToOne @JoinColumn(name = "doctor_id") @NotNull
    private Doctor doctor;
}
