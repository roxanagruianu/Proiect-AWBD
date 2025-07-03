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

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
public class PacientRepositoryTest {

    @Autowired
    private PacientRepository pacientRepository;

    @Test
    public void testCreateAndFindPacient() {
        Pacient pacient = new Pacient();
        pacient.setNume("Ion");
        pacient.setPrenume("Popescu");
        pacient.setEmail("ion.popescu@example.com");

        Pacient salvat = pacientRepository.save(pacient);
        Optional<Pacient> gasit = pacientRepository.findById(salvat.getId());

        Assertions.assertTrue(gasit.isPresent());
        Assertions.assertEquals("Ion", gasit.get().getNume());
    }
}