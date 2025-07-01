package com.example.baithi.VEHICLE_RENTAL_E;



import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Min(value = 1900, message = "Year must be >= 1900")
    private Integer year;

    @Min(value = 0, message = "Price per day must be positive")
    private BigDecimal pricePerDay;
}
