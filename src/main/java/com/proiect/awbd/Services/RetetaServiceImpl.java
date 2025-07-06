package com.proiect.awbd.Services;


import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.Repositories.RetetaRepository;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.data_model.Reteta;
import com.proiect.awbd.dtos.RetetaDTO;
import com.proiect.awbd.mappers.RetetaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetetaServiceImpl implements RetetaService {

    private final RetetaRepository repository;
    private final RetetaMapper mapper;
    private final ProgramareRepository programareRepository;
    private final DoctorRepository doctorRepository;
    private final PacientRepository pacientRepository;

    @Override
    public RetetaDTO save(RetetaDTO dto) {
        Programare programare = programareRepository.findById(dto.getProgramareId())
                .orElseThrow(() -> new RuntimeException("Programare inexistentă"));
        Reteta reteta = mapper.toEntity(dto, programare);
        return mapper.toDTO(repository.save(reteta));
    }

    @Override
    public Optional<RetetaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public Optional<RetetaDTO> findByProgramareId(Long programareId) {
        return repository.findByProgramareId(programareId).map(mapper::toDTO);
    }

    @Override
    public List<RetetaDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<RetetaDTO> findByDoctorUsername(String username) {
        List<Reteta> retete = repository.findByDoctorUsername(username);
        return retete.stream().map(mapper::toDTO).toList();
    }

    public List<RetetaDTO> findByPacientUsername(String username) {
        return repository.findByPacientUsername(username)
                .stream().map(mapper::toDTO).toList();
    }

}
