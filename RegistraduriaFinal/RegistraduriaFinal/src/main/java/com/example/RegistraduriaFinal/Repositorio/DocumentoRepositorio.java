package com.example.RegistraduriaFinal.Repositorio;

import com.example.RegistraduriaFinal.Entidad.Documento;
import com.example.RegistraduriaFinal.Entidad.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {
    @Query("SELECT d FROM Documento d  WHERE d.persona.id = :personaId")
    Optional<Documento> findDocumentoByPersonaId(@Param("personaId") Long personaId);
}
