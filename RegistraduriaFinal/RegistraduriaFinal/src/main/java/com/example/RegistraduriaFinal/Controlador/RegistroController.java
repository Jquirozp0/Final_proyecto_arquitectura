package com.example.RegistraduriaFinal.Controlador;

import com.example.RegistraduriaFinal.Dto.DocumentoDto;
import com.example.RegistraduriaFinal.Dto.PersonaDto;
import com.example.RegistraduriaFinal.Servicio.RegistroService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@Controller
public class RegistroController {

    private static final Logger logger = LogManager.getLogger(RegistroController.class);
    @Autowired
    RegistroService reService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/crear")
    public String agregar(Model model) {
        PersonaDto personaDto = new PersonaDto();
        model.addAttribute("persona", personaDto);
        return "crear_registro";
    }

    @PostMapping("/crear")
    public String agregarRegistro(@ModelAttribute PersonaDto persona) {
        if (persona.getNombrePadre() != null && persona.getNombreMadre() != null) {
            persona.setTipoFormulario(0);
        } else if (persona.getNombreMadre() != null && persona.getNombrePadre() == null) {
            persona.setTipoFormulario(2);
        } else {
            persona.setTipoFormulario(1);
        }
        reService.registrarPersona(persona);
        return "index";

    }


    @GetMapping({"/listar"})
    public String listarRegistros(Model model) {
        logger.info("Verificando ");
        List<PersonaDto> registros = reService.obtenerPersonas();
        List<DocumentoDto> registrosDoc = reService.obtenerPersonasDoc();

        for (PersonaDto registro : registros) {
            for (DocumentoDto documento : registrosDoc) {
                if (registro.getId().equals(documento.getId())) {
                    registro.setDocumento(documento);
                    break;
                }
            }
        }
        model.addAttribute("registros", registros);
        return "listar_registro";
    }

    @GetMapping("/editar/{id}")
    public String modificarRegistro(@PathVariable Long id, Model model) {
        PersonaDto personaExistente = reService.obtenerPersona(id);
        System.out.println(personaExistente);
        model.addAttribute("persona", personaExistente);
        return "editar_registro";
    }

    @PostMapping("/editar/{id}")
    public String editarRegistro(@PathVariable long id, @ModelAttribute PersonaDto personaDto, Model model) {
        System.out.println(personaDto + "hola mundo");
        model.addAttribute("persona", reService.actualizar(id, personaDto));
        return "redirect:/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRegistro(@PathVariable long id) {
        reService.eliminar(id);
        return "redirect:/listar";
    }
}

