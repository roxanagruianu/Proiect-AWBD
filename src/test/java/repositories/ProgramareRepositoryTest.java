package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.ProgramareRepository;
import com.proiect.awbd.data_model.Clinica;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
class ProgramareRepositoryTest {

    @Autowired
    private ProgramareRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveAndFind() {
        Pacient pacient = Pacient.builder()
                .nume("Ion")
                .prenume("Popescu")
                .email("ion@example.com")
                .telefon("+40712345678")
                .build();
        entityManager.persist(pacient);

        Doctor doctor = Doctor.builder()
                .nume("Maria")
                .prenume("Ionescu")
                .specializare("Cardiolog")
                .email("maria@example.com")
                .telefon("+40711223344")
                .build();
        entityManager.persist(doctor);

        Clinica clinica = Clinica.builder()
                .nume("Test Clinica")
                .adresa("Test Adresa")
                .telefon("+40711111111")
                .build();
        entityManager.persist(clinica);

        Programare p = Programare.builder()
                .dataOra(LocalDateTime.now().plusDays(1))
                .pacient(pacient)
                .doctor(doctor)
                .clinica(clinica)
                .observatii("Observatii")
                .build();

        repository.save(p);
        List<Programare> all = repository.findAll();
        assertFalse(all.isEmpty());
    }
}
