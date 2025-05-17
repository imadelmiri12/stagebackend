package ma.zyn.app.unit.service.impl.collaborator.journal;

import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.dao.facade.core.journal.JournalAmelioarationDao;
import ma.zyn.app.service.impl.collaborator.journal.JournalAmelioarationCollaboratorServiceImpl;

import ma.zyn.app.bean.core.journal.TypeAction ;
import ma.zyn.app.bean.core.actor.Collaborator ;
import ma.zyn.app.bean.core.journal.ResultatEvaluation ;
import ma.zyn.app.bean.core.journal.SystemeManagement ;
import ma.zyn.app.bean.core.journal.CritereEvaluation ;
import ma.zyn.app.bean.core.journal.CategorieEcrate ;
import ma.zyn.app.bean.core.journal.OrigineEcrate ;
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
class JournalAmelioarationCollaboratorServiceImplTest {

    @Mock
    private JournalAmelioarationDao repository;
    private AutoCloseable autoCloseable;
    private JournalAmelioarationCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new JournalAmelioarationCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllJournalAmelioaration() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveJournalAmelioaration() {
        // Given
        JournalAmelioaration toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteJournalAmelioaration() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetJournalAmelioarationById() {
        // Given
        Long idToRetrieve = 1L; // Example JournalAmelioaration ID to retrieve
        JournalAmelioaration expected = new JournalAmelioaration(); // You need to replace JournalAmelioaration with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        JournalAmelioaration result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private JournalAmelioaration constructSample(int i) {
		JournalAmelioaration given = new JournalAmelioaration();
        given.setDate(LocalDateTime.now());
        given.setAnnee(i);
        given.setDateButoir(LocalDateTime.now());
        given.setDateRealisation(LocalDateTime.now());
        given.setDateEvaluation(LocalDateTime.now());
        given.setDateCloture(LocalDateTime.now());
        given.setAvancement(BigDecimal.TEN);
        given.setDescriptionEcrat("descriptionEcrat-"+i);
        given.setDescriptionAction("descriptionAction-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setCauseSuppose("causeSuppose-"+i);
        given.setSystemeManagement(new SystemeManagement(1L));
        given.setCategorieEcrate(new CategorieEcrate(1L));
        given.setOrigineEcrate(new OrigineEcrate(1L));
        given.setTypeAction(new TypeAction(1L));
        given.setCollaborator(new Collaborator(1L));
        given.setCritereEvaluation(new CritereEvaluation(1L));
        given.setResultatEvaluation(new ResultatEvaluation(1L));
        return given;
    }

}
