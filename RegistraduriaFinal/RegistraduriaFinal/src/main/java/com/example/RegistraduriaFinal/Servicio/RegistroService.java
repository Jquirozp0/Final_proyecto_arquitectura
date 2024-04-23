package com.example.RegistraduriaFinal.Servicio;

import com.example.RegistraduriaFinal.Dto.DocumentoDto;
import com.example.RegistraduriaFinal.Dto.PersonaDto;
import com.example.RegistraduriaFinal.Entidad.Documento;
import com.example.RegistraduriaFinal.Entidad.Persona;
import com.example.RegistraduriaFinal.Repositorio.DocumentoRepositorio;
import com.example.RegistraduriaFinal.Repositorio.PersonaRepositorio;
import com.example.RegistraduriaFinal.Utilidad.ArchivoUtilidad;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Service
public class RegistroService implements Serializable {

    private ModelMapper modelMapper;

    private final PersonaRepositorio registroRepositorio;
    private final DocumentoRepositorio documentoRepositorio;
    private final ArchivoUtilidad archivoUtilidad;

    public PersonaDto registrarPersona(PersonaDto personaDto) {
        archivoUtilidad.asignarApellidos(personaDto);
        personaDto.setId(archivoUtilidad.generarId());
        DocumentoDto documentoDto = new DocumentoDto();
        documentoDto.setId(personaDto.getId());
        documentoDto.setTipoDocumento(archivoUtilidad.asignarTipoDocumento(personaDto.getFechaNacimiento()));
        Documento documento = documentoRepositorio.save(modelMapper.map(documentoDto, Documento.class));
        personaDto.setDocumento(modelMapper.map(documento, DocumentoDto.class));
        Persona persona = registroRepositorio.save(modelMapper.map(personaDto, Persona.class));
        return modelMapper.map(persona, PersonaDto.class);
    }

    public List<PersonaDto> obtenerPersonas() {
        TypeToken<List<PersonaDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(registroRepositorio.findAll(), typeToken.getType());
    }

    public List<DocumentoDto> obtenerPersonasDoc() {
        TypeToken<List<DocumentoDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(documentoRepositorio.findAll(), typeToken.getType());
    }

    public PersonaDto obtenerPersona(long serial) {
        Persona persona = registroRepositorio.findById(serial).orElseThrow(
        );
        return modelMapper.map(persona, PersonaDto.class);
    }

    public DocumentoDto obtenerDocumento(long serial) {
        Documento documento = documentoRepositorio.findById(serial).orElseThrow(
        );
        return modelMapper.map(documento, DocumentoDto.class);
    }

    public PersonaDto actualizar(long id, PersonaDto personaDto) {
        //obtner informacion de registro existente
        PersonaDto personaDtoExistente = obtenerPersona(id);
        DocumentoDto documentoDtoExistente = obtenerDocumento(id);

        if (personaDto.getDocumento() == null) {
            personaDto.setDocumento(personaDtoExistente.getDocumento());
        } else {
            if (personaDto.getDocumento().getId() == null) {
                personaDto.getDocumento().setId(personaDtoExistente.getDocumento().getId());
            }
        }
        //actualizar informacion
        documentoDtoExistente.setTipoDocumento(archivoUtilidad.asignarTipoDocumento(personaDto.getFechaNacimiento()));
        personaDtoExistente.setDocumento(modelMapper.map(documentoDtoExistente, DocumentoDto.class));
        personaDto.setDocumento(personaDtoExistente.getDocumento());
        //guardar informacion
        Documento documentoAct = documentoRepositorio.save(modelMapper.map(documentoDtoExistente, Documento.class));
        modelMapper.map(documentoAct, DocumentoDto.class);
        Persona persona = registroRepositorio.save(modelMapper.map(personaDto, Persona.class));
        return modelMapper.map(persona, PersonaDto.class);
    }

    public void eliminar(long serial) {
        registroRepositorio.deleteById(serial);
    }

}



