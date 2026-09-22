package com.example.demo.Persistance.Entity;

import com.example.demo.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "email")
    private String correo;
    @Column(name = "password")
    private String contraseña;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Rol rol;
}
