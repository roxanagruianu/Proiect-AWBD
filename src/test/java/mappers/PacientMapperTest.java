package mappers;

import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.dtos.PacientDTO;
import com.proiect.awbd.mappers.PacientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PacientMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private PacientMapper pacientMapper = new PacientMapper(modelMapper);

    @Test
    public void testToDtoAndBack() {
        Pacient pacient = Pacient.builder()
                .id(1L)
                .nume("Ionescu")
                .prenume("Maria")
                .email("maria@example.com")
                .telefon("+40712345678")
                .build();

        PacientDTO dto = pacientMapper.toDTO(pacient);
        assertEquals("Ionescu", dto.getNume());

        Pacient mappedBack = pacientMapper.toEntity(dto);
        assertEquals("Ionescu", mappedBack.getNume());
    }
}