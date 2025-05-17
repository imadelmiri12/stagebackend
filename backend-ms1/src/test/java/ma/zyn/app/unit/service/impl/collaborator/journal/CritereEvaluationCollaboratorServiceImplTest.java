package ma.zyn.app.unit.service.impl.collaborator.journal;

import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.dao.facade.core.journal.CritereEvaluationDao;
import ma.zyn.app.service.impl.collaborator.journal.CritereEvaluationCollaboratorServiceImpl;

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
class CritereEvaluationCollaboratorServiceImplTest {

    @Mock
    private CritereEvaluationDao repository;
    private AutoCloseable autoCloseable;
    private CritereEvaluationCollaboratorServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CritereEvaluationCollaboratorServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCritereEvaluation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCritereEvaluation() {
        // Given
        CritereEvaluation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCritereEvaluation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCritereEvaluationById() {
        // Given
        Long idToRetrieve = 1L; // Example CritereEvaluation ID to retrieve
        CritereEvaluation expected = new CritereEvaluation(); // You need to replace CritereEvaluation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        CritereEvaluation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private CritereEvaluation constructSample(int i) {
		CritereEvaluation given = new CritereEvaluation();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
