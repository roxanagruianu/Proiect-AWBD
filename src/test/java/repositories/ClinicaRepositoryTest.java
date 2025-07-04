package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.ClinicaRepository;
import com.proiect.awbd.data_model.Clinica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class ClinicaRepositoryTest {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Test
    public void testSaveAndFindByNume() {
        Clinica clinica = Clinica.builder()
                .nume("Test Clinica")
                .adresa("Test Adresa")
                .telefon("+40711111111")
                .build();

        clinicaRepository.save(clinica);

        Optional<Clinica> found = clinicaRepository.findByNume("Test Clinica");

        assertTrue(found.isPresent());
        assertEquals("Test Clinica", found.get().getNume());
    }

    @Test
    public void testDeleteById() {
        Clinica clinica = Clinica.builder()
                .nume("Clinica X")
                .adresa("Strada Z")
                .telefon("+40722222222")
                .build();

        Clinica saved = clinicaRepository.save(clinica);
        Long id = saved.getId();

        clinicaRepository.deleteById(id);

        assertFalse(clinicaRepository.findById(id).isPresent());
    }
}
