package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.DiagnosticRepository;
import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
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
    private final PacientRepository pacientiRepository;
    private final DoctorRepository doctorRepository;

    public DiagnosticServiceImpl(DiagnosticRepository repository, DiagnosticMapper mapper, ProgramareRepository programareRepository, PacientRepository pacientiRepository, DoctorRepository doctorRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.programareRepository = programareRepository;
        this.pacientiRepository = pacientiRepository;
        this.doctorRepository = doctorRepository;
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

    @Override
    public List<DiagnosticDTO> findByDoctorUsername(String username) {
        Doctor doctor = doctorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Doctorul nu a fost găsit"));
        List<Diagnostic> diagnostice = repository.findByDoctor(doctor);
        return diagnostice.stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<DiagnosticDTO> findByPacientUsername(String username) {
        Pacient pacient = pacientiRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Pacientul nu a fost găsit"));
        List<Diagnostic> diagnostice = repository.findByPacient(pacient);
        return diagnostice.stream().map(mapper::toDTO).toList();
    }

}
