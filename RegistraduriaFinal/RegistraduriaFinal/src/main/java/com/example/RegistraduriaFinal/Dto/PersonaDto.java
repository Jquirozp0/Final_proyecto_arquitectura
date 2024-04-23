package com.example.RegistraduriaFinal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonaDto implements Serializable {
    private long tipoFormulario;
    private Long id;
    @NotBlank(message = "El nombre del nuevo resgistro es obligatorio")
    private String primerNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private DocumentoDto documento;
    @NotBlank(message = "El nombre del padre es obligatorio")
    private String nombrePadre;
    @NotBlank(message = "El primer apellido del padre es obligatorio")
    private String apellidoPadre;
    @NotBlank(message = "El segundo apellido del padre es obligatorio")
    private String apellido2Padre;
    @NotBlank(message = "El nombre de la madre es obligatorio")
    private String nombreMadre;
    @NotBlank(message = "El primer apellido de la madre es obligatorio")
    private String apellidoMadre;
    @NotBlank(message = "El segundo apellido de la madre es obligatorio")
    private String apellido2Madre;
}