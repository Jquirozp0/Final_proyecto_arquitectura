package com.example.RegistraduriaFinal.Utilidad;

import com.example.RegistraduriaFinal.Dto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

@Data
@AllArgsConstructor
public class ArchivoUtilidad {

    private String primerApellido;
    private String segundoApellido;

    public ArchivoUtilidad() {
    }

    public void asignarApellidos(PersonaDto personaDto) {
        if (personaDto.getTipoFormulario() == 0) {
            personaDto.setPrimerApellido(personaDto.getApellidoPadre());
            personaDto.setSegundoApellido(personaDto.getApellidoMadre());
        } else if (personaDto.getTipoFormulario() == 1) {
            personaDto.setPrimerApellido(personaDto.getApellidoPadre());
            personaDto.setSegundoApellido(personaDto.getApellido2Padre());
            personaDto.setNombreMadre("NA");
            personaDto.setApellidoMadre("NA");
            personaDto.setApellido2Madre("NA");
        } else if (personaDto.getTipoFormulario() == 2) {
            personaDto.setPrimerApellido(personaDto.getApellidoMadre());
            personaDto.setSegundoApellido(personaDto.getApellido2Madre());
            personaDto.setNombrePadre("NA");
            personaDto.setApellidoPadre("NA");
            personaDto.setApellido2Padre("NA");
        }
    }

    public String asignarTipoDocumento(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        String tipoDocumento = "";
        if (edad < 7) {
            tipoDocumento = "Registro Civil";
        } else if (edad >= 7 && edad < 18) {
            tipoDocumento = "Tarjeta de Identidad";
        } else if (edad >= 18) {
            tipoDocumento = "Cédula de Ciudadanía";
        }
        return tipoDocumento;
    }

    public long generarId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        // Genera 10 dígitos aleatorios
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10)); // Agrega un dígito aleatorio (0-9)
        }
        String idString = sb.toString();
        // Convierte la cadena a tipo long
        long id = Long.parseLong(idString);
        return id;
    }
}
