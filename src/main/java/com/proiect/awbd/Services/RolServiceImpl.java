package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.dtos.RolDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Override
    public RolDTO save(RolDTO dto) {
        Rol rol = modelMapper.map(dto, Rol.class);
        Rol saved = rolRepository.save(rol);
        return modelMapper.map(saved, RolDTO.class);
    }

    @Override
    public List<RolDTO> findAll() {
        return rolRepository.findAll()
                .stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        return rolRepository.findById(id)
                .map(rol -> modelMapper.map(rol, RolDTO.class));
    }

    @Override
    public Optional<RolDTO> findByNume(String nume) {
        return rolRepository.findByNume(nume)
                .map(rol -> modelMapper.map(rol, RolDTO.class));
    }
}
