package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.data_model.Utilizator;
import com.proiect.awbd.dtos.UtilizatorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Utilizator.class, UtilizatorDTO.class).addMappings(m ->
                m.<Set<String>>map(
                        src -> {
                            if (src.getRoluri() == null) {
                                return Collections.emptySet();
                            }
                            return src.getRoluri().stream()
                                    .map(Rol::getNume)
                                    .collect(Collectors.toSet());
                        },
                        UtilizatorDTO::setRoluri
                )
        );

        return mapper;
    }
}
