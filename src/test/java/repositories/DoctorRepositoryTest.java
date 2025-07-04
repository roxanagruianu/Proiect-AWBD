package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.data_model.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testSaveAndFindById() {
        Doctor doctor = Doctor.builder()
                .nume("Popescu")
                .prenume("Andrei")
                .specializare("Cardiologie")
                .email("andrei.popescu@exemplu.com")
                .telefon("+40712345678")
                .build();

        Doctor saved = doctorRepository.save(doctor);
        Optional<Doctor> found = doctorRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Popescu", found.get().getNume());
    }

    @Test
    public void testFindByEmail() {
        Doctor doctor = Doctor.builder()
                .nume("Ionescu")
                .prenume("Maria")
                .specializare("Dermatologie")
                .email("maria.ionescu@exemplu.com")
                .telefon("+40798765432")
                .build();

        doctorRepository.save(doctor);
        Optional<Doctor> found = doctorRepository.findByEmail("maria.ionescu@exemplu.com");

        assertTrue(found.isPresent());
        assertEquals("Dermatologie", found.get().getSpecializare());
    }
}
