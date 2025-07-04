package services;

import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Services.PacientServiceImpl;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.dtos.PacientDTO;
import com.proiect.awbd.mappers.PacientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacientServiceImplTest {

    @Mock
    private PacientRepository pacientRepository;

    @Mock
    private PacientMapper pacientMapper;

    @InjectMocks
    private PacientServiceImpl pacientService;

    @Test
    public void testFindAllPacients() {
        Pacient pacient = new Pacient();
        pacient.setId(1L);
        pacient.setNume("Popescu");
        pacient.setPrenume("Ion");
        pacient.setEmail("ion.popescu@example.com");
        pacient.setTelefon("+40712345678");
        pacient.setDataNasterii(LocalDate.of(1990, 1, 1));

        PacientDTO pacientDTO = new PacientDTO();
        pacientDTO.setId(1L);
        pacientDTO.setNume("Popescu");
        pacientDTO.setPrenume("Ion");
        pacientDTO.setEmail("ion.popescu@example.com");
        pacientDTO.setTelefon("+40712345678");
        pacientDTO.setDataNasterii(LocalDate.of(1990, 1, 1));

        when(pacientRepository.findAll(Sort.by("nume"))).thenReturn(List.of(pacient));
        when(pacientMapper.toDTO(pacient)).thenReturn(pacientDTO);

        List<PacientDTO> result = pacientService.findAll();

        assertEquals(1, result.size());
        assertEquals("Popescu", result.get(0).getNume());
        assertEquals("Ion", result.get(0).getPrenume());
    }

    @Test
    public void testFindByIdPacient() {
        Pacient pacient = new Pacient();
        pacient.setId(1L);
        pacient.setNume("Popescu");

        PacientDTO pacientDTO = new PacientDTO();
        pacientDTO.setId(1L);
        pacientDTO.setNume("Popescu");

        when(pacientRepository.findById(1L)).thenReturn(Optional.of(pacient));
        when(pacientMapper.toDTO(pacient)).thenReturn(pacientDTO);

        PacientDTO result = pacientService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Popescu", result.getNume());
    }

    @Test
    public void testSavePacient() {
        PacientDTO dto = new PacientDTO();
        dto.setNume("Ionescu");
        dto.setPrenume("Maria");

        Pacient pacient = new Pacient();
        pacient.setNume("Ionescu");
        pacient.setPrenume("Maria");

        when(pacientMapper.toEntity(dto)).thenReturn(pacient);
        when(pacientRepository.save(pacient)).thenReturn(pacient);
        when(pacientMapper.toDTO(pacient)).thenReturn(dto);

        PacientDTO saved = pacientService.save(dto);

        assertEquals("Ionescu", saved.getNume());
        assertEquals("Maria", saved.getPrenume());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(pacientRepository).deleteById(1L);

        pacientService.deleteById(1L);

        verify(pacientRepository, times(1)).deleteById(1L);
    }
}