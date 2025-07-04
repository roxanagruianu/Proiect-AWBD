package services;

import com.proiect.awbd.Repositories.ClinicaRepository;
import com.proiect.awbd.Services.ClinicaServiceImpl;
import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.dtos.ClinicaDTO;
import com.proiect.awbd.mappers.ClinicaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClinicaServiceImplTest {

    @Mock
    private ClinicaRepository clinicaRepository;

    @Mock
    private ClinicaMapper clinicaMapper;

    @InjectMocks
    private ClinicaServiceImpl clinicaService;

    @Test
    public void testFindAll() {
        Clinica clinica = new Clinica();
        clinica.setId(1L);
        clinica.setNume("Clinica A");

        when(clinicaRepository.findAll()).thenReturn(List.of(clinica));
        when(clinicaMapper.toDTO(clinica)).thenReturn(new ClinicaDTO(1L, "Clinica A", null, null));

        List<ClinicaDTO> clinici = clinicaService.findAll();

        assertEquals(1, clinici.size());
        assertEquals("Clinica A", clinici.get(0).getNume());
    }

    @Test
    public void testSave() {
        ClinicaDTO dto = new ClinicaDTO(1L, "Clinica Save", "Adresa", "+40700000000");
        Clinica entity = Clinica.builder().id(1L).nume("Clinica Save").build();

        when(clinicaMapper.toEntity(dto)).thenReturn(entity);
        when(clinicaRepository.save(entity)).thenReturn(entity);
        when(clinicaMapper.toDTO(entity)).thenReturn(dto);

        ClinicaDTO savedDTO = clinicaService.save(dto);

        assertEquals("Clinica Save", savedDTO.getNume());
        verify(clinicaRepository).save(entity);
    }

    @Test
    public void testFindByIdNotFound() {
        when(clinicaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clinicaService.findById(99L));
    }
}
