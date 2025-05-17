package ma.zyn.app.unit.service.impl.admin.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDao;
import ma.zyn.app.service.impl.admin.mensuel.RapportEvenementMensuelSecteurAdminServiceImpl;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail ;
import ma.zyn.app.bean.core.commun.Secteur ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class RapportEvenementMensuelSecteurAdminServiceImplTest {

    @Mock
    private RapportEvenementMensuelSecteurDao repository;
    private AutoCloseable autoCloseable;
    private RapportEvenementMensuelSecteurAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RapportEvenementMensuelSecteurAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRapportEvenementMensuelSecteur() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRapportEvenementMensuelSecteur() {
        // Given
        RapportEvenementMensuelSecteur toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRapportEvenementMensuelSecteur() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRapportEvenementMensuelSecteurById() {
        // Given
        Long idToRetrieve = 1L; // Example RapportEvenementMensuelSecteur ID to retrieve
        RapportEvenementMensuelSecteur expected = new RapportEvenementMensuelSecteur(); // You need to replace RapportEvenementMensuelSecteur with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RapportEvenementMensuelSecteur result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RapportEvenementMensuelSecteur constructSample(int i) {
		RapportEvenementMensuelSecteur given = new RapportEvenementMensuelSecteur();
        given.setRapportEvenementMensuel(new RapportEvenementMensuel(1L));
        given.setSecteur(new Secteur(1L));
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setTotalCumul(BigDecimal.TEN);
        given.setIndicateurGlobalCumul(BigDecimal.TEN);
        List<RapportEvenementMensuelSecteurDetail> rapportEvenementMensuelSecteurDetails = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RapportEvenementMensuelSecteurDetail element = new RapportEvenementMensuelSecteurDetail();
                                                element.setId((long)id);
                                                element.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur(Long.valueOf(1)));
                                                element.setTotal(new BigDecimal(2*10));
                                                element.setIndicateurGlobal(new BigDecimal(3*10));
                                                element.setTypeEvenement(new TypeEvenement(Long.valueOf(4)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRapportEvenementMensuelSecteurDetails(rapportEvenementMensuelSecteurDetails);
        return given;
    }

}
