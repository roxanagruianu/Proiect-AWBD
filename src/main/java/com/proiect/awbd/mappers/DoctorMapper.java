package com.proiect.awbd.mappers;

import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.dtos.DoctorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    private final ModelMapper modelMapper;

    public DoctorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DoctorDTO toDTO(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    public Doctor toEntity(DoctorDTO dto) {
        Doctor doctor = modelMapper.map(dto, Doctor.class);
        if (doctor.getId() != null && doctor.getId() == 0L) {
            doctor.setId(null);
        }
        return doctor;
    }
}
