package mappers;

import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.dtos.DoctorDTO;
import com.proiect.awbd.mappers.DoctorMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private DoctorMapper doctorMapper = new DoctorMapper(modelMapper);

    @Test
    public void testToDtoAndBack() {
        Doctor doctor = Doctor.builder()
                .id(1L)
                .nume("Popescu")
                .prenume("Ion")
                .email("ion@example.com")
                .telefon("+40712345678")
                .specializare("Cardiolog")
                .build();

        DoctorDTO dto = doctorMapper.toDTO(doctor);
        assertEquals("Popescu", dto.getNume());

        Doctor mappedBack = doctorMapper.toEntity(dto);
        assertEquals("Popescu", mappedBack.getNume());
    }
}
