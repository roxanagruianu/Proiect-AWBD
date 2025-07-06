package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.data_model.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
class RolRepositoryTest {

    @Autowired
    private RolRepository rolRepository;

    @Test
    void testFindByNume() {
        Rol rol = Rol.builder().nume("ADMIN").build();
        rolRepository.save(rol);

        Optional<Rol> found = rolRepository.findByNume("ADMIN");

        assertTrue(found.isPresent());
        assertEquals("ADMIN", found.get().getNume());
    }

    @Test
    void testExistsByNume() {
        rolRepository.save(Rol.builder().nume("USER").build());

        assertTrue(rolRepository.existsByNume("USER"));
        assertFalse(rolRepository.existsByNume("MANAGER"));
    }
}
