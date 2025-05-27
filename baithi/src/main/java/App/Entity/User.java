package App.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Size(min = 3)
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull @Size(min = 6)
    private String password;

    // Getters và Setters
}