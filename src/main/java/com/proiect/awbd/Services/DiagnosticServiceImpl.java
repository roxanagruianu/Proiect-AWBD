package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.DiagnosticRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.DiagnosticDTO;
import com.proiect.awbd.mappers.DiagnosticMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository repository;
    private final DiagnosticMapper mapper;
    private final ProgramareRepository programareRepository;

    public DiagnosticServiceImpl(DiagnosticRepository repository, DiagnosticMapper mapper, ProgramareRepository programareRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.programareRepository = programareRepository;
    }

    @Override
    public DiagnosticDTO save(DiagnosticDTO dto) {
        Programare programare = programareRepository.findById(dto.getProgramareId())
                .orElseThrow(() -> new RuntimeException("Programarea nu a fost gasita"));
        Diagnostic diagnostic = mapper.toEntity(dto, programare);
        return mapper.toDTO(repository.save(diagnostic));
    }

    @Override
    public Optional<DiagnosticDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public Optional<DiagnosticDTO> findByProgramareId(Long programareId) {
        return repository.findByProgramareId(programareId).map(mapper::toDTO);
    }

    @Override
    public List<DiagnosticDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
