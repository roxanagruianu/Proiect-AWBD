package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.Repositories.UtilizatorRepository;
import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.data_model.Utilizator;
import com.proiect.awbd.dtos.UtilizatorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilizatorServiceImpl implements UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UtilizatorDTO save(UtilizatorDTO dto) {
        Utilizator utilizator = modelMapper.map(dto, Utilizator.class);
        utilizator.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Rol> roluri = new HashSet<>();
        for (String rolNume : dto.getRoluri()) {
            roluri.add(rolRepository.findByNume(rolNume).orElseThrow());
        }
        utilizator.setRoluri(roluri);

        return modelMapper.map(utilizatorRepository.save(utilizator), UtilizatorDTO.class);
    }

    @Override
    public Optional<UtilizatorDTO> findByUsername(String username) {
        return utilizatorRepository.findByUsernameWithRoles(username)
                .map(utilizator -> {
                    UtilizatorDTO dto = modelMapper.map(utilizator, UtilizatorDTO.class);

                    Set<String> roluriNume = utilizator.getRoluri().stream()
                            .map(Rol::getNume)
                            .collect(Collectors.toSet());

                    dto.setRoluri(roluriNume);

                    System.out.println("Mapped roles:");
                    dto.getRoluri().forEach(r ->
                            System.out.println(" - " + r + " (class: " + r.getClass().getName() + ")")
                    );

                    System.out.println("Original roles from entity:");
                    utilizator.getRoluri().forEach(rol ->
                            System.out.println(" - " + rol + " (class: " + rol.getClass().getName() + ")")
                    );

                    return dto;
                });
    }

    @Override
    public List<UtilizatorDTO> findAll() {
        return utilizatorRepository.findAll().stream()
                .map(utilizator -> modelMapper.map(utilizator, UtilizatorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UtilizatorDTO> findById(Long id) {
        return utilizatorRepository.findById(id)
                .map(utilizator -> modelMapper.map(utilizator, UtilizatorDTO.class));
    }

    @Override
    public void deleteById(Long id) {
        utilizatorRepository.deleteById(id);
    }

    @Override
    public UtilizatorDTO update(UtilizatorDTO dto) {
        Utilizator utilizator = utilizatorRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));

        utilizator.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            utilizator.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        Set<Rol> roluri = dto.getRoluri().stream()
                .map(rol -> rolRepository.findByNume(rol).orElseThrow(() ->
                        new RuntimeException("Rolul nu a fost găsit: " + rol)))
                .collect(Collectors.toSet());
        utilizator.setRoluri(roluri);

        return modelMapper.map(utilizatorRepository.save(utilizator), UtilizatorDTO.class);
    }


}
