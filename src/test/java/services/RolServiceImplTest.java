package services;

import com.proiect.awbd.Repositories.RolRepository;
import com.proiect.awbd.Services.RolServiceImpl;
import com.proiect.awbd.data_model.Rol;
import com.proiect.awbd.dtos.RolDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolServiceImplTest {

    @Mock
    private RolRepository rolRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RolServiceImpl rolService;

    @Test
    void testSave() {
        RolDTO dto = new RolDTO(null, "ADMIN");
        Rol rol = new Rol(null, "ADMIN", null);
        Rol saved = new Rol(1L, "ADMIN", null);
        RolDTO savedDto = new RolDTO(1L, "ADMIN");

        when(modelMapper.map(dto, Rol.class)).thenReturn(rol);
        when(rolRepository.save(rol)).thenReturn(saved);
        when(modelMapper.map(saved, RolDTO.class)).thenReturn(savedDto);

        RolDTO result = rolService.save(dto);

        assertEquals(1L, result.getId());
        assertEquals("ADMIN", result.getNume());
    }

    @Test
    void testFindByNume() {
        Rol rol = new Rol(1L, "USER", null);
        RolDTO dto = new RolDTO(1L, "USER");

        when(rolRepository.findByNume("USER")).thenReturn(Optional.of(rol));
        when(modelMapper.map(rol, RolDTO.class)).thenReturn(dto);

        Optional<RolDTO> result = rolService.findByNume("USER");

        assertTrue(result.isPresent());
        assertEquals("USER", result.get().getNume());
    }
}
