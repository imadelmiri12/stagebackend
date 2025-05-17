package ma.zyn.app.unit.service.impl.collaborator.mensuel;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.dao.facade.core.mensuel.RapportEvenementMensuelDao;
import ma.zyn.app.service.impl.collaborator.mensuel.RapportEvenementMensuelCollaboratorServiceImpl;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel ;
import ma.zyn.app.bean.core.commun.Secteur ;
import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelTypeEvenement ;
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
class RapportEvenementMensuelCollaboratorServiceImplTest {

    @Mock
    private RapportEvenementMensuelDao repository;
    private AutoCloseable autoCloseable;
    private RapportEvenementMensuelCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RapportEvenementMensuelCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRapportEvenementMensuel() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRapportEvenementMensuel() {
        // Given
        RapportEvenementMensuel toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRapportEvenementMensuel() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRapportEvenementMensuelById() {
        // Given
        Long idToRetrieve = 1L; // Example RapportEvenementMensuel ID to retrieve
        RapportEvenementMensuel expected = new RapportEvenementMensuel(); // You need to replace RapportEvenementMensuel with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RapportEvenementMensuel result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RapportEvenementMensuel constructSample(int i) {
		RapportEvenementMensuel given = new RapportEvenementMensuel();
        given.setCode("code-"+i);
        given.setDateEmission(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setMois(i);
        given.setAnnee(i);
        given.setNombreMouvement(i);
        given.setTotal(BigDecimal.TEN);
        given.setIndicateurGlobal(BigDecimal.TEN);
        given.setNombreMouvementCumul(i);
        given.setTotalCumul(BigDecimal.TEN);
        given.setIndicateurGlobalCumul(BigDecimal.TEN);
        List<RapportEvenementMensuelSecteur> rapportEvenementMensuelSecteurs = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RapportEvenementMensuelSecteur element = new RapportEvenementMensuelSecteur();
                                                element.setId((long)id);
                                                element.setRapportEvenementMensuel(new RapportEvenementMensuel(Long.valueOf(1)));
                                                element.setSecteur(new Secteur(Long.valueOf(2)));
                                                element.setTotal(new BigDecimal(3*10));
                                                element.setIndicateurGlobal(new BigDecimal(4*10));
                                                element.setTotalCumul(new BigDecimal(5*10));
                                                element.setIndicateurGlobalCumul(new BigDecimal(6*10));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRapportEvenementMensuelSecteurs(rapportEvenementMensuelSecteurs);
        List<RapportEvenementMensuelTypeEvenement> rapportEvenementMensuelTypeEvenements = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RapportEvenementMensuelTypeEvenement element = new RapportEvenementMensuelTypeEvenement();
                                                element.setId((long)id);
                                                element.setRapportEvenementMensuel(new RapportEvenementMensuel(Long.valueOf(1)));
                                                element.setTypeEvenement(new TypeEvenement(Long.valueOf(2)));
                                                element.setTotal(new BigDecimal(3*10));
                                                element.setTotalCumul(new BigDecimal(4*10));
                                                element.setIndicateurGlobalCumul(new BigDecimal(5*10));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRapportEvenementMensuelTypeEvenements(rapportEvenementMensuelTypeEvenements);
        return given;
    }

}
