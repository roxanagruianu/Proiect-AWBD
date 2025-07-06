package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.ClinicaRepository;
import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.ProgramareDTO;
import com.proiect.awbd.mappers.ProgramareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramareServiceImpl implements ProgramareService {

    private final ProgramareRepository programareRepository;
    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicaRepository clinicaRepository;
    private final ProgramareMapper programareMapper;

    @Override
    public List<ProgramareDTO> findAll() {
        return programareRepository.findAll()
                .stream()
                .map(programareMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramareDTO findById(Long id) {
        return programareRepository.findById(id)
                .map(programareMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Programarea nu a fost gasita"));
    }

    @Override
    public void save(ProgramareDTO dto) {
        Pacient pacient = pacientRepository.findById(dto.getPacientId()).orElseThrow();
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow();
        Clinica clinica = dto.getClinicaId() != null ? clinicaRepository.findById(dto.getClinicaId()).orElse(null) : null;
        Programare programare = programareMapper.toEntity(dto, pacient, doctor, clinica);
        programareRepository.save(programare);
    }

    @Override
    public void delete(Long id) {
        programareRepository.deleteById(id);
    }

    @Override
    public List<ProgramareDTO> findByPacientId(Long pacientId) {
        return programareRepository.findByPacientId(pacientId).stream()
                .map(programareMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgramareDTO> findByDoctorId(Long doctorId) {
        return programareRepository.findByDoctorId(doctorId).stream()
                .map(programareMapper::toDTO)
                .collect(Collectors.toList());
    }
}
