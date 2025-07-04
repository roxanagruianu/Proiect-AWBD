package mappers;

import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.DiagnosticDTO;
import com.proiect.awbd.mappers.DiagnosticMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiagnosticMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private DiagnosticMapper mapper = new DiagnosticMapper(modelMapper);

    @Test
    void testToDtoAndBack() {
        Programare prog = Programare.builder().id(1L).build();
        Diagnostic diag = Diagnostic.builder()
                .id(10L)
                .detalii("Febră mare, tuse")
                .codDiagnostic("J11")
                .data(LocalDate.now())
                .programare(prog)
                .build();

        DiagnosticDTO dto = mapper.toDTO(diag);
        assertEquals("J11", dto.getCodDiagnostic());

        Diagnostic back = mapper.toEntity(dto, prog);
        assertEquals("Febră mare, tuse", back.getDetalii());
    }
}
