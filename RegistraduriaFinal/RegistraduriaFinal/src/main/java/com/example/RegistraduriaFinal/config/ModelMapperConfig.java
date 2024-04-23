package com.example.RegistraduriaFinal.config;

import com.example.RegistraduriaFinal.Utilidad.ArchivoUtilidad;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ArchivoUtilidad archivoUtilidad() {
        return new ArchivoUtilidad();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
