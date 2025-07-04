package services;

import com.proiect.awbd.Repositories.DiagnosticRepository;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.Services.DiagnosticServiceImpl;
import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.DiagnosticDTO;
import com.proiect.awbd.mappers.DiagnosticMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiagnosticServiceImplTest {

    @Mock
    private DiagnosticRepository diagnosticRepository;

    @Mock
    private DiagnosticMapper diagnosticMapper;

    @Mock
    private ProgramareRepository programareRepository;

    @InjectMocks
    private DiagnosticServiceImpl diagnosticService;

    @Test
    void testSaveDiagnostic() {
        DiagnosticDTO dto = new DiagnosticDTO();
        dto.setDetalii("Febră și tuse");
        dto.setCodDiagnostic("J10");
        dto.setData(LocalDate.now());
        dto.setProgramareId(1L);

        Programare programare = Programare.builder()
                .id(1L)
                .dataOra(LocalDateTime.now().plusDays(1))
                .build();

        Diagnostic diagnostic = Diagnostic.builder()
                .id(1L)
                .detalii("Febră și tuse")
                .codDiagnostic("J10")
                .data(dto.getData())
                .programare(programare)
                .build();

        when(programareRepository.findById(1L)).thenReturn(Optional.of(programare));
        when(diagnosticMapper.toEntity(dto, programare)).thenReturn(diagnostic);
        when(diagnosticRepository.save(diagnostic)).thenReturn(diagnostic);
        when(diagnosticMapper.toDTO(diagnostic)).thenReturn(dto);

        DiagnosticDTO result = diagnosticService.save(dto);

        assertEquals("Febră și tuse", result.getDetalii());
        assertEquals("J10", result.getCodDiagnostic());
        assertEquals(1L, result.getProgramareId());
    }
}


