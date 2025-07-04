package mappers;

import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.dtos.ClinicaDTO;
import com.proiect.awbd.mappers.ClinicaMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClinicaMapperTest {

    private ModelMapper modelMapper = new ModelMapper();
    private ClinicaMapper clinicaMapper = new ClinicaMapper(modelMapper);

    @Test
    public void testToDtoAndBack() {
        Clinica clinica = Clinica.builder()
                .id(1L)
                .nume("Clinica Test")
                .adresa("Strada Test")
                .telefon("+40711111111")
                .build();

        ClinicaDTO dto = clinicaMapper.toDTO(clinica);
        assertEquals("Clinica Test", dto.getNume());

        Clinica mappedBack = clinicaMapper.toEntity(dto);
        assertEquals("Clinica Test", mappedBack.getNume());
    }
}
