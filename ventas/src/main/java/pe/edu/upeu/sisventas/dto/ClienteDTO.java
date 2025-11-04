package pe.edu.upeu.sisventas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClienteDTO {
    private Long id;

    @NotNull(message = "Los nombres no pueden ser nulos")
    @Size(min = 3, max = 100, message = "Los nombres deben tener entre 3 y 100 caracteres")
    private String nombres;

    @NotNull(message = "Los apellidos no pueden ser nulos")
    @Size(min = 3, max = 100, message = "Los apellidos deben tener entre 3 y 100 caracteres")
    private String apellidos;

    @NotNull(message = "El DNI no puede ser nulo")
    @Size(min = 7, max = 15, message = "El DNI debe tener entre 7 y 15 caracteres")
    private String dni;



    private List<Long> ventasIds;
}