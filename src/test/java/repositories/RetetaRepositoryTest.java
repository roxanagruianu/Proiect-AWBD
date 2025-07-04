package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.RetetaRepository;
import com.proiect.awbd.data_model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
class RetetaRepositoryTest {

    @Autowired
    private RetetaRepository retetaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByProgramareId() {
        Clinica clinica = Clinica.builder()
                .nume("Clinica Test")
                .adresa("Str. Test 1")
                .telefon("+40712345678")
                .build();
        entityManager.persist(clinica);

        Doctor doctor = Doctor.builder()
                .nume("Popescu")
                .prenume("Ion")
                .specializare("Cardiologie")
                .email("ion.popescu@example.com")
                .telefon("+40711122233")
                .build();
        entityManager.persist(doctor);

        Pacient pacient = Pacient.builder()
                .nume("Ionescu")
                .prenume("Maria")
                .email("maria.ionescu@example.com")
                .telefon("+40799988877")
                .build();
        entityManager.persist(pacient);

        Programare programare = Programare.builder()
                .dataOra(LocalDateTime.now().plusDays(1))
                .clinica(clinica)
                .doctor(doctor)
                .pacient(pacient)
                .build();
        entityManager.persist(programare);

        Reteta reteta = Reteta.builder()
                .medicamente("Paracetamol")
                .instructiuni("1 dimineața, 1 seara")
                .dataEmitere(LocalDate.now())
                .programare(programare)
                .build();
        entityManager.persist(reteta);
        entityManager.flush();

        Optional<Reteta> found = retetaRepository.findByProgramareId(programare.getId());

        assertTrue(found.isPresent());
        assertEquals("Paracetamol", found.get().getMedicamente());
    }

}
