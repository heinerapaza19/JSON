package pe.edu.upeu.sisventas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(exclude = "ventas")
@ToString(exclude = "ventas")
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotNull @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String apellidos;

    @NotNull @Size(min = 7, max = 15)
    @Column(nullable = false, unique = true, length = 15)
    private String dni;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<VentaEntity> ventas = new ArrayList<>();




}