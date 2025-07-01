package com.example.baithi.VEHICLE_RENTAL_E;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Data
public class Rental {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Rental date is required")
    private LocalDate rentalDate;

    @NotNull(message = "Return date is required")
    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;

    @ManyToOne @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer must be selected")
    private Customer customer;

    @ManyToOne @JoinColumn(name = "vehicle_id")
    @NotNull(message = "Vehicle must be selected")
    private Vehicle vehicle;
}
