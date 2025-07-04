package services;

import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.Repositories.RetetaRepository;
import com.proiect.awbd.Services.RetetaServiceImpl;
import com.proiect.awbd.data_model.Reteta;
import com.proiect.awbd.dtos.RetetaDTO;
import com.proiect.awbd.mappers.RetetaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetetaServiceImplTest {

    @Mock
    private RetetaRepository retetaRepository;
    @Mock
    private RetetaMapper retetaMapper;
    @Mock
    private ProgramareRepository programareRepository;

    @InjectMocks
    private RetetaServiceImpl retetaService;

    @Test
    void testFindAll() {
        Reteta reteta = new Reteta();
        RetetaDTO dto = new RetetaDTO();
        when(retetaRepository.findAll()).thenReturn(List.of(reteta));
        when(retetaMapper.toDTO(reteta)).thenReturn(dto);

        List<RetetaDTO> retete = retetaService.findAll();

        assertEquals(1, retete.size());
        verify(retetaRepository, times(1)).findAll();
        verify(retetaMapper, times(1)).toDTO(reteta);
    }
}

