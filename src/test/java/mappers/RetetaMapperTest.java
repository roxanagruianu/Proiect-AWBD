package mappers;

import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.data_model.Reteta;
import com.proiect.awbd.dtos.RetetaDTO;
import com.proiect.awbd.mappers.RetetaMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetetaMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private RetetaMapper mapper = new RetetaMapper(modelMapper);

    @Test
    void testToDtoAndBack() {
        Programare prog = Programare.builder().id(1L).build();
        Reteta reteta = Reteta.builder()
                .id(20L)
                .medicamente("Paracetamol")
                .instructiuni("3x/zi după masă")
                .dataEmitere(LocalDate.now())
                .programare(prog)
                .build();

        RetetaDTO dto = mapper.toDTO(reteta);
        assertEquals("Paracetamol", dto.getMedicamente());

        Reteta back = mapper.toEntity(dto, prog);
        assertEquals("3x/zi după masă", back.getInstructiuni());
    }
}
