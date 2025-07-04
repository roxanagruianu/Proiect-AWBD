package services;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.RecenzieRepository;
import com.proiect.awbd.Services.RecenzieServiceImpl;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Recenzie;
import com.proiect.awbd.dtos.RecenzieDTO;
import com.proiect.awbd.mappers.RecenzieMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecenzieServiceImplTest {

    @Mock
    private RecenzieRepository recenzieRepository;

    @Mock
    private RecenzieMapper recenzieMapper;

    @Mock
    private PacientRepository pacientRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private RecenzieServiceImpl recenzieService;

    @Test
    void testSaveRecenzie() {
        RecenzieDTO dto = new RecenzieDTO();
        dto.setPacientId(1L);
        dto.setDoctorId(2L);
        dto.setComentariu("Comentariu test");
        dto.setRating(5);

        Pacient pacient = Pacient.builder().id(1L).build();
        Doctor doctor = Doctor.builder().id(2L).build();
        Recenzie entity = Recenzie.builder().build();
        Recenzie savedEntity = Recenzie.builder().id(100L).build();
        RecenzieDTO savedDto = new RecenzieDTO();
        savedDto.setId(100L);

        when(pacientRepository.findById(1L)).thenReturn(Optional.of(pacient));
        when(doctorRepository.findById(2L)).thenReturn(Optional.of(doctor));
        when(recenzieMapper.toEntity(dto, pacient, doctor)).thenReturn(entity);
        when(recenzieRepository.save(entity)).thenReturn(savedEntity);
        when(recenzieMapper.toDTO(savedEntity)).thenReturn(savedDto);

        RecenzieDTO result = recenzieService.save(dto);

        assertEquals(100L, result.getId());
    }

    @Test
    void testFindById() {
        Recenzie entity = Recenzie.builder().id(1L).build();
        RecenzieDTO dto = new RecenzieDTO();
        dto.setId(1L);

        when(recenzieRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(recenzieMapper.toDTO(entity)).thenReturn(dto);

        Optional<RecenzieDTO> result = recenzieService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindAll() {
        Recenzie entity = Recenzie.builder().id(1L).build();
        RecenzieDTO dto = new RecenzieDTO();
        dto.setId(1L);

        when(recenzieRepository.findAll()).thenReturn(List.of(entity));
        when(recenzieMapper.toDTO(entity)).thenReturn(dto);

        List<RecenzieDTO> result = recenzieService.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testDeleteById() {
        doNothing().when(recenzieRepository).deleteById(1L);

        recenzieService.deleteById(1L);

        verify(recenzieRepository, times(1)).deleteById(1L);
    }
}
