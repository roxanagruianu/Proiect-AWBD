package mappers;

import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import com.proiect.awbd.dtos.ProgramareDTO;
import com.proiect.awbd.mappers.ProgramareMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramareMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private ProgramareMapper mapper = new ProgramareMapper(modelMapper);

    @Test
    void testToDtoAndBack() {
        Pacient pacient = Pacient.builder().id(1L).build();
        Doctor doctor = Doctor.builder().id(2L).build();
        Clinica clinica = Clinica.builder().id(3L).build();

        Programare programare = Programare.builder()
                .id(10L)
                .dataOra(LocalDateTime.now().plusDays(1))
                .observatii("Control")
                .pacient(pacient)
                .doctor(doctor)
                .clinica(clinica)
                .build();

        ProgramareDTO dto = mapper.toDTO(programare);
        assertEquals(1L, dto.getPacientId());
        assertEquals(2L, dto.getDoctorId());

        Programare back = mapper.toEntity(dto, pacient, doctor, clinica);
        assertEquals("Control", back.getObservatii());
    }
}
