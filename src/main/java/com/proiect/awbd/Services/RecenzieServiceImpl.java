package com.proiect.awbd.Services;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.RecenzieRepository;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Recenzie;
import com.proiect.awbd.dtos.RecenzieDTO;
import com.proiect.awbd.mappers.RecenzieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecenzieServiceImpl implements RecenzieService {

    private final RecenzieRepository recenzieRepository;
    private final RecenzieMapper recenzieMapper;
    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public RecenzieDTO save(RecenzieDTO dto) {
        Pacient pacient = pacientRepository.findById(dto.getPacientId())
                .orElseThrow(() -> new RuntimeException("Pacientul nu a fost gasit"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctorul nu  a fost gasit"));

        Recenzie recenzie = recenzieMapper.toEntity(dto, pacient, doctor);
        Recenzie saved = recenzieRepository.save(recenzie);
        return recenzieMapper.toDTO(saved);
    }

    @Override
    public Optional<RecenzieDTO> findById(Long id) {
        return recenzieRepository.findById(id).map(recenzieMapper::toDTO);
    }

    @Override
    public List<RecenzieDTO> findAll() {
        return recenzieRepository.findAll().stream()
                .map(recenzieMapper::toDTO)
                .toList();
    }

    @Override
    public List<RecenzieDTO> findByDoctorId(Long doctorId) {
        return recenzieRepository.findByDoctorId(doctorId).stream()
                .map(recenzieMapper::toDTO)
                .toList();
    }

    @Override
    public List<RecenzieDTO> findByPacientId(Long pacientId) {
        return recenzieRepository.findByPacientId(pacientId).stream()
                .map(recenzieMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        recenzieRepository.deleteById(id);
    }
}
