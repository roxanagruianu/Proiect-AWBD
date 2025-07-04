package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.DiagnosticRepository;
import com.proiect.awbd.data_model.Diagnostic;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Programare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class DiagnosticRepositoryTest {

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByProgramareId() {
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

        Programare programare = Programare.builder()
                .dataOra(LocalDateTime.now().plusDays(1))
                .pacient(pacient)
                .doctor(doctor)
                .build();
        entityManager.persist(programare);

        Diagnostic diagnostic = Diagnostic.builder()
                .detalii("Durere in piept")
                .codDiagnostic("I20")
                .data(LocalDate.now())
                .programare(programare)
                .build();
        entityManager.persist(diagnostic);

        Optional<Diagnostic> found = diagnosticRepository.findByProgramareId(programare.getId());

        assertTrue(found.isPresent());
        assertEquals("I20", found.get().getCodDiagnostic());
    }

}