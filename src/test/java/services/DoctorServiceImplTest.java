package services;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Services.DoctorServiceImpl;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.dtos.DoctorDTO;
import com.proiect.awbd.mappers.DoctorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Test
    public void testFindAllDoctors() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setNume("Test");

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setNume("Test");

        when(doctorRepository.findAll()).thenReturn(List.of(doctor));
        when(doctorMapper.toDTO(doctor)).thenReturn(doctorDTO);

        List<DoctorDTO> result = doctorService.findAll();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getNume());
    }

    @Test
    public void testSaveDoctor() {
        DoctorDTO dto = new DoctorDTO();
        dto.setNume("Nou");

        Doctor doctor = new Doctor();
        doctor.setNume("Nou");

        when(doctorMapper.toEntity(dto)).thenReturn(doctor);
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        when(doctorMapper.toDTO(doctor)).thenReturn(dto);

        DoctorDTO saved = doctorService.save(dto);

        assertEquals("Nou", saved.getNume());
    }
}
