package ma.zyn.app.unit.service.impl.collaborator.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelSecteurDetailDao;
import ma.zyn.app.service.impl.collaborator.mensuel.RapportEvenementMensuelSecteurDetailCollaboratorServiceImpl;

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
class RapportEvenementMensuelSecteurDetailCollaboratorServiceImplTest {

    @Mock
    private RapportEvenementMensuelSecteurDetailDao repository;
    private AutoCloseable autoCloseable;
    private RapportEvenementMensuelSecteurDetailCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RapportEvenementMensuelSecteurDetailCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRapportEvenementMensuelSecteurDetail() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRapportEvenementMensuelSecteurDetail() {
        // Given
        RapportEvenementMensuelSecteurDetail toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRapportEvenementMensuelSecteurDetail() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRapportEvenementMensuelSecteurDetailById() {
        // Given
        Long idToRetrieve = 1L; // Example RapportEvenementMensuelSecteurDetail ID to retrieve
        RapportEvenementMensuelSecteurDetail expected = new RapportEvenementMensuelSecteurDetail(); // You need to replace RapportEvenementMensuelSecteurDetail with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RapportEvenementMensuelSecteurDetail result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RapportEvenementMensuelSecteurDetail constructSample(int i) {
		RapportEvenementMensuelSecteurDetail given = new RapportEvenementMensuelSecteurDetail();
        given.setRapportEvenementMensuelSecteur(new RapportEvenementMensuelSecteur(1L));
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setTypeEvenement(new TypeEvenement(1L));
        return given;
    }

}
