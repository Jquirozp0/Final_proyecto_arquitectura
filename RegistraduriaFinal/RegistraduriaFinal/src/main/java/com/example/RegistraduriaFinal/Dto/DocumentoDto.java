package com.example.RegistraduriaFinal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentoDto implements Serializable {
    private Long id;
    private String tipoDocumento;
}
