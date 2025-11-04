package pe.edu.upeu.sisventas.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class CategoriaDTO {
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El código no puede ser nulo")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    private String codigo;

    private Date fechaCreacion;

    private Date fechaModificacion;
    private List<Integer> precios;

}