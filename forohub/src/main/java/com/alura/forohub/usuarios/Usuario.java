package com.alura.forohub.usuarios;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "El id no puede estar en blanco")
    private Long id;
    @NotBlank(message = "El usuario no puede estar en blanco")
    private String username;
    @NotBlank(message = "El password no puede estar en blanco")
    private String password;
    @NotBlank(message = "El rol no puede estar en blanco")
    private String role;

    // Getters y setters
}
