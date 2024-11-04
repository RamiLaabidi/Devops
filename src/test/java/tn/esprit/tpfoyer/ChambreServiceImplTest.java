package tn.esprit.tpfoyer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllChambres() {
        List<Chambre> chambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void retrieveChambre() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);

        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void addChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertNotNull(result);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void modifyChambre() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);

        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);

        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void removeChambre() {
        Long chambreId = 1L;
        doNothing().when(chambreRepository).deleteById(chambreId);

        chambreService.removeChambre(chambreId);

        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Test
    void recupererChambresSelonTyp() {
        Chambre chambre = new Chambre();
        chambre.setTypeC(TypeChambre.SIMPLE);
        List<Chambre> chambres = List.of(chambre);

        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(chambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        assertEquals(1, result.size());
        assertEquals(TypeChambre.SIMPLE, result.get(0).getTypeC());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void trouverchambreSelonEtudiant() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        long cin = 123456;

        when(chambreRepository.trouverChselonEt(cin)).thenReturn(chambre);

        Chambre result = chambreService.trouverchambreSelonEtudiant(cin);

        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).trouverChselonEt(cin);
    }
}
