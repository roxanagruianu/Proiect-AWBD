package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.dtos.PacientDTO;
import com.proiect.awbd.mappers.PacientMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacientServiceImpl implements PacientService {

    private final PacientRepository pacientRepository;
    private final PacientMapper pacientMapper;

    public PacientServiceImpl(PacientRepository pacientRepository, PacientMapper pacientMapper) {
        this.pacientRepository = pacientRepository;
        this.pacientMapper = pacientMapper;
    }

    @Override
    public List<PacientDTO> findAll() {
        return pacientRepository.findAll(Sort.by("nume"))
                .stream()
                .map(pacientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacientDTO findById(Long id) {
        Optional<Pacient> pacient = pacientRepository.findById(id);
        return pacient.map(pacientMapper::toDTO).orElseThrow(() -> new RuntimeException("Pacient nu a fost gasit"));
    }

    @Override
    public PacientDTO save(PacientDTO pacientDTO) {
        Pacient pacient = pacientMapper.toEntity(pacientDTO);
        return pacientMapper.toDTO(pacientRepository.save(pacient));
    }

    @Override
    public void deleteById(Long id) {
        pacientRepository.deleteById(id);
    }
}