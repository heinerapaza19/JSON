package com.gestion.clinica.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "medico")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false('', ', unique = true')[field.get('unique', False)])
    private String nombre;

    @Column(nullable = false('', ', unique = true')[field.get('unique', False)])
    private String apellido;

    @Column(nullable = false('', ', unique = true')[field.get('unique', False)])
    private String especialidad;

    @Column(nullable = false('', ', unique = true')[field.get('unique', False)])
    private String numeroColegiado;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaEntity> citas = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaModificacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now();
    }

}
