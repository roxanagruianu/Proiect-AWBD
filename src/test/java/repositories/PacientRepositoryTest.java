package repositories;

import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.data_model.Pacient;
import com.proiect.awbd.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class PacientRepositoryTest {

    @Autowired
    private PacientRepository pacientRepository;

    @Test
    public void testSaveAndFindById() {
        Pacient pacient = new Pacient();
        pacient.setNume("Ion");
        pacient.setPrenume("Popescu");
        pacient.setEmail("ion.popescu@example.com");
        pacient.setTelefon("+40712345678");
        pacient.setDataNasterii(LocalDate.of(1990, 1, 1));

        Pacient salvat = pacientRepository.save(pacient);
        Optional<Pacient> gasit = pacientRepository.findById(salvat.getId());

        Assertions.assertTrue(gasit.isPresent());
        Assertions.assertEquals("Ion", gasit.get().getNume());
    }

    @Test
    public void testFindByEmail() {
        Pacient pacient = new Pacient();
        pacient.setNume("Maria");
        pacient.setPrenume("Ionescu");
        pacient.setEmail("maria.ionescu@example.com");
        pacient.setTelefon("+40765432109");
        pacient.setDataNasterii(LocalDate.of(1985, 5, 15));

        pacientRepository.save(pacient);

        Optional<Pacient> gasit = pacientRepository.findByEmail("maria.ionescu@example.com");
        Assertions.assertTrue(gasit.isPresent());
        Assertions.assertEquals("Maria", gasit.get().getNume());
    }
}
