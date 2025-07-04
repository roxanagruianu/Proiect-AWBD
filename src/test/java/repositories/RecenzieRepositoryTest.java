package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.RecenzieRepository;
import com.proiect.awbd.data_model.Doctor;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.data_model.Recenzie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class RecenzieRepositoryTest {

    @Autowired
    private RecenzieRepository recenzieRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByDoctorId() {
        Pacient pacient = Pacient.builder()
                .nume("Popescu")
                .prenume("Ion")
                .build();
        entityManager.persist(pacient);

        Doctor doctor = entityManager.persist(Doctor.builder().nume("D").prenume("D").specializare("ORL").build());


        Recenzie recenzie = Recenzie.builder()
                .comentariu("Foarte bun doctor")
                .rating(5)
                .dataRecenzie(LocalDateTime.now())
                .pacient(pacient)
                .doctor(doctor)
                .build();
        entityManager.persist(recenzie);
        entityManager.flush();

        List<Recenzie> found = recenzieRepository.findByDoctorId(doctor.getId());

        assertFalse(found.isEmpty());
        assertEquals("Foarte bun doctor", found.get(0).getComentariu());
    }
}

