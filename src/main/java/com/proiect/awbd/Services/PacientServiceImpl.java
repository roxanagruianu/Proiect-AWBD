package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.data_model.Doctor;
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
    private final DoctorRepository doctorRepository;
    private final ProgramareRepository programareRepository;

    public PacientServiceImpl(PacientRepository pacientRepository, PacientMapper pacientMapper, DoctorRepository doctorRepository, ProgramareRepository programareRepository) {
        this.pacientRepository = pacientRepository;
        this.pacientMapper = pacientMapper;
        this.doctorRepository = doctorRepository;
        this.programareRepository = programareRepository;
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

    @Override
    public List<PacientDTO> findPacientiByDoctorEmail(String doctorEmail) {
        Doctor doctor = doctorRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctorul nu a fost găsit"));

        List<Pacient> pacienti = programareRepository.findDistinctPacientiByDoctor(doctor);
        return pacienti.stream()
                .map(pacientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PacientDTO> findPacientiByDoctorUsername(String username) {
        Doctor doctor = doctorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Doctorul nu a fost găsit"));

        List<Pacient> pacienti = programareRepository.findDistinctPacientiByDoctor(doctor);
        return pacienti.stream()
                .map(pacientMapper::toDTO)
                .collect(Collectors.toList());
    }


}