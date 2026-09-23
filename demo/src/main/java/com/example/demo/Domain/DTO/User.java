package com.example.demo.Domain.DTO;

import com.example.demo.enums.Rol;
import jakarta.validation.constraints.Email;import jakarta.validation.constraints.NotBlank;import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @Email(message = "el correo no tiene formato valido")
    @NotBlank(message = "El correo es obligatorio")
    private String mail;
    @NotBlank(message = "la contraseña es obligatoria")
    private String password;
    private Rol role;
}
