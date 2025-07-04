package services;

import com.proiect.awbd.Repositories.ClinicaRepository;
import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.Services.ProgramareServiceImpl;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.ProgramareDTO;
import com.proiect.awbd.mappers.ProgramareMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramareServiceImplTest {

    @Mock
    private ProgramareRepository repository;
    @Mock private PacientRepository pacientRepo;
    @Mock private DoctorRepository doctorRepo;
    @Mock private ClinicaRepository clinicaRepo;
    @Mock private ProgramareMapper mapper;

    @InjectMocks
    private ProgramareServiceImpl service;

    @Test
    void testFindAll() {
        Programare p = new Programare();
        ProgramareDTO dto = new ProgramareDTO();
        when(repository.findAll()).thenReturn(List.of(p));
        when(mapper.toDTO(p)).thenReturn(dto);

        List<ProgramareDTO> list = service.findAll();
        assertEquals(1, list.size());
    }
}
