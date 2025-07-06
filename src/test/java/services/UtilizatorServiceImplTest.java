package services;

import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.Repositories.UtilizatorRepository;
import com.proiect.awbd.Services.UtilizatorServiceImpl;
import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.data_model.Utilizator;
import com.proiect.awbd.dtos.UtilizatorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UtilizatorServiceImplTest {

    @Mock
    private UtilizatorRepository utilizatorRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UtilizatorServiceImpl utilizatorService;

    @Test
    void testSave() {
        UtilizatorDTO dto = new UtilizatorDTO();
        dto.setUsername("john");
        dto.setPassword("parola123");
        dto.setRoluri(Set.of("USER"));

        Utilizator entity = new Utilizator();
        entity.setUsername("john");
        entity.setPassword("encoded");

        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNume("USER");

        Utilizator savedEntity = new Utilizator();
        savedEntity.setId(1L);
        savedEntity.setUsername("john");
        savedEntity.setRoluri(Set.of(rol));

        UtilizatorDTO savedDto = new UtilizatorDTO();
        savedDto.setId(1L);
        savedDto.setUsername("john");
        savedDto.setRoluri(Set.of("USER"));

        when(modelMapper.map(dto, Utilizator.class)).thenReturn(entity);
        when(passwordEncoder.encode("parola123")).thenReturn("encoded");
        when(rolRepository.findByNume("USER")).thenReturn(Optional.of(rol));
        when(utilizatorRepository.save(entity)).thenReturn(savedEntity);
        when(modelMapper.map(savedEntity, UtilizatorDTO.class)).thenReturn(savedDto);

        UtilizatorDTO result = utilizatorService.save(dto);

        assertEquals("john", result.getUsername());
        assertTrue(result.getRoluri().contains("USER"));
    }

    @Test
    void testFindByUsername() {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(2L);
        utilizator.setUsername("maria");

        UtilizatorDTO dto = new UtilizatorDTO();
        dto.setId(2L);
        dto.setUsername("maria");

        when(utilizatorRepository.findByUsername("maria")).thenReturn(Optional.of(utilizator));
        when(modelMapper.map(utilizator, UtilizatorDTO.class)).thenReturn(dto);

        Optional<UtilizatorDTO> result = utilizatorService.findByUsername("maria");

        assertTrue(result.isPresent());
        assertEquals("maria", result.get().getUsername());
    }
}
