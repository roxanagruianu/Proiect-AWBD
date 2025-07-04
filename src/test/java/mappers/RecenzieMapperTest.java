package mappers;

import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Recenzie;
import com.proiect.awbd.dtos.RecenzieDTO;
import com.proiect.awbd.mappers.RecenzieMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecenzieMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private RecenzieMapper mapper = new RecenzieMapper(modelMapper);

    @Test
    void testToDtoAndBack() {
        Pacient pacient = Pacient.builder().id(1L).build();
        Doctor doctor = Doctor.builder().id(2L).build();

        Recenzie recenzie = Recenzie.builder()
                .id(10L)
                .comentariu("Comentariu test")
                .rating(4)
                .dataRecenzie(LocalDateTime.now())
                .pacient(pacient)
                .doctor(doctor)
                .build();

        RecenzieDTO dto = mapper.toDTO(recenzie);
        assertEquals(10L, dto.getId());
        assertEquals(1L, dto.getPacientId());
        assertEquals(2L, dto.getDoctorId());
        assertEquals("Comentariu test", dto.getComentariu());
        assertEquals(4, dto.getRating());

        Recenzie back = mapper.toEntity(dto, pacient, doctor);
        assertEquals("Comentariu test", back.getComentariu());
        assertEquals(4, back.getRating());
        assertEquals(pacient, back.getPacient());
        assertEquals(doctor, back.getDoctor());
    }
}
