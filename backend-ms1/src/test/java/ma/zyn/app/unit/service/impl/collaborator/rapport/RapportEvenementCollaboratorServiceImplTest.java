package ma.zyn.app.unit.service.impl.collaborator.rapport;

import ma.zyn.app.bean.core.rapport.RapportEvenement;
import ma.zyn.app.dao.facade.core.rapport.RapportEvenementDao;
import ma.zyn.app.service.impl.collaborator.rapport.RapportEvenementCollaboratorServiceImpl;

import ma.zyn.app.bean.core.actor.Collaborator ;
import ma.zyn.app.bean.core.commun.TypeRapportEvenement ;
import ma.zyn.app.bean.core.commun.Vessel ;
import ma.zyn.app.bean.core.commun.Port ;
import ma.zyn.app.bean.core.commun.Secteur ;
import ma.zyn.app.bean.core.commun.TypeEvenement ;
import ma.zyn.app.bean.core.journal.JournalAmelioaration ;
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
class RapportEvenementCollaboratorServiceImplTest {

    @Mock
    private RapportEvenementDao repository;
    private AutoCloseable autoCloseable;
    private RapportEvenementCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RapportEvenementCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRapportEvenement() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRapportEvenement() {
        // Given
        RapportEvenement toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRapportEvenement() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRapportEvenementById() {
        // Given
        Long idToRetrieve = 1L; // Example RapportEvenement ID to retrieve
        RapportEvenement expected = new RapportEvenement(); // You need to replace RapportEvenement with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RapportEvenement result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RapportEvenement constructSample(int i) {
		RapportEvenement given = new RapportEvenement();
        given.setCode("code-"+i);
        given.setDateEvenement(LocalDateTime.now());
        given.setDateSoumission(LocalDateTime.now());
        given.setAttachments("attachments-"+i);
        given.setDescription("description-"+i);
        given.setRecommendation("recommendation-"+i);
        given.setDirectivePmjChef("directivePmjChef-"+i);
        given.setPort(new Port(1L));
        given.setMois(i);
        given.setAnnee(i);
        given.setWindDirection(BigDecimal.TEN);
        given.setWindForce(BigDecimal.TEN);
        given.setCurrentDirection(BigDecimal.TEN);
        given.setCurrentForce(BigDecimal.TEN);
        given.setSwellHeigth(BigDecimal.TEN);
        given.setSwellDirection(BigDecimal.TEN);
        given.setTypeEvenement(new TypeEvenement(1L));
        given.setCollaborator(new Collaborator(1L));
        given.setVessel(new Vessel(1L));
        given.setFonde(false);
        given.setTypeRapportEvenement(new TypeRapportEvenement(1L));
        given.setSecteur(new Secteur(1L));
        given.setJournalAmelioaration(new JournalAmelioaration(1L));
        return given;
    }

}
