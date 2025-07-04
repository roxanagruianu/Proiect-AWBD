package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.ClinicaRepository;
import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.dtos.ClinicaDTO;
import com.proiect.awbd.mappers.ClinicaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaRepository clinicaRepository;
    private final ClinicaMapper clinicaMapper;

    public ClinicaServiceImpl(ClinicaRepository clinicaRepository, ClinicaMapper clinicaMapper) {
        this.clinicaRepository = clinicaRepository;
        this.clinicaMapper = clinicaMapper;
    }

    @Override
    public List<ClinicaDTO> findAll() {
        return clinicaRepository.findAll()
                .stream()
                .map(clinicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicaDTO findById(Long id) {
        return clinicaRepository.findById(id)
                .map(clinicaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Clinica nu a fost găsită"));
    }

    @Override
    public ClinicaDTO save(ClinicaDTO clinicaDTO) {
        Clinica clinica = clinicaMapper.toEntity(clinicaDTO);
        return clinicaMapper.toDTO(clinicaRepository.save(clinica));
    }

    @Override
    public void deleteById(Long id) {
        clinicaRepository.deleteById(id);
    }
}
