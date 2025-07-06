package repositories;

import com.proiect.awbd.Main;
import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.Repositories.UtilizatorRepository;
import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.data_model.Utilizator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@ContextConfiguration(classes = Main.class)
class UtilizatorRepositoryTest {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @Autowired
    private RolRepository rolRepository;

    @Test
    void testFindByUsername() {
        Rol rol = rolRepository.save(Rol.builder().nume("USER").build());

        Utilizator utilizator = Utilizator.builder()
                .username("testuser")
                .password("secret")
                .roluri(Set.of(rol))
                .build();
        utilizatorRepository.save(utilizator);

        Optional<Utilizator> found = utilizatorRepository.findByUsername("testuser");

        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    void testExistsByUsername() {
        utilizatorRepository.save(Utilizator.builder()
                .username("existing")
                .password("pass")
                .build());

        assertTrue(utilizatorRepository.existsByUsername("existing"));
        assertFalse(utilizatorRepository.existsByUsername("missing"));
    }
}
