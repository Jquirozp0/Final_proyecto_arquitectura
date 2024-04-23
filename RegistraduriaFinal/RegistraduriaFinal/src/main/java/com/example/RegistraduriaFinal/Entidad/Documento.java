package com.example.RegistraduriaFinal.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Documento")
@Table(name = "DOCUMENTOS")
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTOS")
    @SequenceGenerator(name = "SEQ_DOCUMENTOS", sequenceName = "SEQ_DOCUMENTOS", allocationSize = 1)
    @Column(name = "DOC_ID")
    private Long id;

    @OneToOne(mappedBy = "documento", cascade = CascadeType.ALL)
    private Persona persona;

    @Column(name = "DOC_TIPO_DOCUMENTO")
    private String tipoDocumento;

}



